package com.virusbear.tinn

import com.virusbear.tinn.ProcessingContext.Element
import com.virusbear.tinn.ProcessingContext.Key

interface ProcessingContext {
    operator fun <E: Element> get(key: Key<E>): E?

    operator fun <E: Element> contains(key: Key<E>): Boolean =
        get(key) != null

    fun <R> fold(initial: R, operation: (R, Element) -> R): R

    operator fun plus(context: ProcessingContext): ProcessingContext =
        if(context === EmptyProcessingContext) this else
            context.fold(this) { acc, element ->
                val removed = acc.minusKey(element.key)
                if(removed === EmptyProcessingContext) element else {
                    CombinedProcessingContext(removed, element)
                }
            }

    fun minusKey(key: Key<*>): ProcessingContext

    interface Key<E: Element>

    interface Element: ProcessingContext {
        val key: Key<*>

        override operator fun <E: Element> get(key: Key<E>): E? =
            @Suppress("UNCHECKED_CAST")
            if(this.key == key) this as E else null

        override fun <R> fold(initial: R, operation: (R, Element) -> R): R =
            operation(initial, this)

        override fun minusKey(key: Key<*>): ProcessingContext =
            if(this.key == key) EmptyProcessingContext else this
    }
}

abstract class AbstractProcessingContextElement(override val key: Key<*>): Element

abstract class AbstractProcessingContextKey<B: Element, E: B>(
    baseKey: Key<B>,
    private val safeCast: (element: Element) -> E?
): Key<E> {
    private val topmostKey: Key<*> = if(baseKey is AbstractProcessingContextKey<*, *>) baseKey.topmostKey else baseKey

    internal fun tryCast(element: Element): E? = safeCast(element)
    internal fun isSubKey(key: Key<*>): Boolean = key === this || topmostKey === key
}

fun <E: Element> Element.getPolymorphicElement(key: Key<E>): E? {
    if(key is AbstractProcessingContextKey<*, *>) {
        @Suppress("UNCHECKED_CAST")
        return if(key.isSubKey(this.key)) key.tryCast(this) as? E else null
    }

    @Suppress("UNCHECKED_CAST")
    return if(this.key === key) this as E else null
}

object EmptyProcessingContext: ProcessingContext {
    override fun <E: Element> get(key: Key<E>): E? = null
    override fun <R> fold(initial: R, operation: (R, Element) -> R): R = initial
    override fun plus(context: ProcessingContext): ProcessingContext = context
    override fun minusKey(key: Key<*>): ProcessingContext = this
    override fun hashCode(): Int = 0
    override  fun toString(): String = "EmptyProcessingContext"
}

internal class CombinedProcessingContext(
    private val left: ProcessingContext,
    private val element: Element
): ProcessingContext {
    override fun <E: Element> get(key: Key<E>): E? {
        var cur = this
        while(true) {
            cur.element[key]?.let { return it }
            val next = cur.left
            if(next is CombinedProcessingContext) {
                cur = next
            } else {
                return next[key]
            }
        }
    }

    override fun <R> fold(initial: R, operation: (R, Element) -> R): R =
        operation(left.fold(initial, operation), element)

    override fun minusKey(key: Key<*>): ProcessingContext {
        element[key]?.let { return left }
        val newLeft = left.minusKey(key)
        return when {
            newLeft === left -> this
            newLeft === EmptyProcessingContext -> element
            else -> CombinedProcessingContext(newLeft, element)
        }
    }

    private fun size(): Int {
        var cur = this
        var size = 2
        while (true) {
            cur = cur.left as? CombinedProcessingContext ?: return size
            size++
        }
    }

    private fun contains(element: Element): Boolean =
        get(element.key) == element

    private fun containsAll(context: CombinedProcessingContext): Boolean {
        var cur = context
        while (true) {
            if (!contains(cur.element)) return false
            val next = cur.left
            if (next is CombinedProcessingContext) {
                cur = next
            } else {
                return contains(next as Element)
            }
        }
    }

    override fun equals(other: Any?): Boolean =
        this === other || other is CombinedProcessingContext && other.size() == size() && other.containsAll(this)

    override fun hashCode(): Int =
        left.hashCode() + element.hashCode()

    override fun toString(): String =
        "[" + fold("") { acc, element ->
            if (acc.isEmpty()) element.toString() else "$acc, $element"
        } + "]"
}