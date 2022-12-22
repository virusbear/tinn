package com.virusbear.tinn.nodes

abstract class NodeIdentifier(
    val name: String,
    val category: NodeCategory,
    private val factory: NodeFactory
) {
    fun new(): Node =
        factory()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NodeIdentifier) return false

        if (name != other.name) return false
        if (category != other.category) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + category.hashCode()
        return result
    }
}