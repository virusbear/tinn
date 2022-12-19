package com.virusbear.tinn.nodes

import io.github.classgraph.ClassGraph

typealias Factory<T> = () -> T
typealias NodeFactory = Factory<Node>

object NodeManager {
    val hierarchy = NodeCategoryTree()

    fun register(identifier: NodeIdentifier) {
        hierarchy += identifier
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
            NodeManager.register(it)
        }
    }
}