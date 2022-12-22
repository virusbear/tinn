package com.virusbear.tinn.nodes

import io.github.classgraph.ClassGraph
import kotlin.reflect.KClass

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
        val nodes = ClassGraph().enableAllInfo().scan().let { result ->
            result.getClassesWithAnnotation(Register::class.java) intersect result.getSubclasses(NodeIdentifier::class.java)
        }

        nodes.mapNotNull {
            it.loadClass().kotlin.objectInstance as? NodeIdentifier
        }.forEach {
            register(it)
        }
    }
}