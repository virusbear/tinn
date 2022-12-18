package com.virusbear.tinn.nodes

abstract class NodeIdentifier(
    val name: String,
    val category: NodeCategory,
    private val factory: NodeFactory
) {
    fun new(): Node =
        factory()
}