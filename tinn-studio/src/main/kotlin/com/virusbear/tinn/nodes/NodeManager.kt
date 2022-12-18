package com.virusbear.tinn.nodes

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
}