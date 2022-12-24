package com.virusbear.tinn.nodes

import io.github.classgraph.ClassGraph
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotations
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.staticProperties
import kotlin.reflect.jvm.kotlinFunction
import kotlin.reflect.jvm.kotlinProperty

typealias Factory<T> = () -> T
typealias NodeFactory = Factory<Node>

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