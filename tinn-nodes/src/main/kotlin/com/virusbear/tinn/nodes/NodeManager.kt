package com.virusbear.tinn.nodes

import io.github.classgraph.ClassGraph
import kotlin.reflect.KClass

typealias NodeFactory = (NodeIdentifier) -> Node

object NodeManager {
    val hierarchy = NodeCategoryTree()
    private val _portTypes = HashSet<KClass<*>>()
    val portTypes: Set<KClass<*>>
        get() = _portTypes

    fun register(identifier: NodeIdentifier) {
        hierarchy += identifier

        identifier.new().ports.forEach {
            registerPortType(it.type)
        }
    }

    fun registerPortType(type: KClass<*>) {
        _portTypes += type
    }

    fun resolveNodeIdentifier(category: String, name: String): NodeIdentifier? {
        var identifier: NodeIdentifier? = null
        val nodeCategory = NodeCategory.fromString(category)
        hierarchy.walk({}) {
            if(it.category == nodeCategory && it.name == name) {
                identifier = it
            }
        }

        return identifier
    }

    fun walk(onCategory: (NodeCategory) -> Unit, onNode: (NodeIdentifier) -> Unit) {
        hierarchy.walk(onCategory, onNode)
    }

    fun load() {
        ClassGraph().enableAllInfo().scan().use { result ->
            val nodeTypes = (result.getClassesWithAnnotation(Register::class.java) intersect result.getSubclasses(NodeIdentifier::class.java)).mapNotNull {
                it.loadClass().kotlin.objectInstance as? NodeIdentifier
            }
            val nodeDefinitions = result.getClassesWithMethodAnnotation(Register::class.java).flatMap {
                val annotatedMethods = it.declaredMethodInfo.filter { "annotations" in it.name }.filter { it.hasAnnotation(Register::class.java) }.map { it.name.substringBefore("$") }
                it.declaredMethodInfo.filter { it.name in annotatedMethods && it.isStatic && it.isFinal }.map {
                    it.loadClassAndGetMethod()
                }.filter { it.returnType == NodeIdentifier::class.java }.mapNotNull {
                    it.invoke(null) as? NodeIdentifier?
                }
            }

            nodeTypes union nodeDefinitions
        }.forEach {
            register(it)
        }
    }
}