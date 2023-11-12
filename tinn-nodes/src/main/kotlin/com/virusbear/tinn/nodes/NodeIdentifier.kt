package com.virusbear.tinn.nodes

import com.virusbear.tinn.AbstractContextElement
import com.virusbear.tinn.ProcessingContext
import com.virusbear.tinn.EmptyContext

open class NodeIdentifier(
    val name: String,
    val category: NodeCategory,
    val internal: Boolean = false,
    private val factory: NodeFactory
): AbstractContextElement(NodeIdentifier) {
    companion object Key: ProcessingContext.Key<NodeIdentifier>

    fun new(context: ProcessingContext = EmptyContext): Node =
        factory(context + this)

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