package com.virusbear.tinn.nodes

import com.virusbear.tinn.BaseDestroyable
import java.util.BitSet
import java.util.Stack
import kotlin.reflect.KProperty

class Nodespace: BaseDestroyable() {
    companion object {
        val current: Nodespace
            get() {
                require(nodespaces.isNotEmpty()) { "No nodespace in current context" }

                return nodespaces.peek()
            }

        private val nodespaces = Stack<Nodespace>()

        fun push(nodespace: Nodespace) {
            nodespaces.push(nodespace)
        }
        fun pop() {
            nodespaces.pop()
        }
    }

    val nodeIds = IdPool()
    val portIds = IdPool()
    val linkIds = IdPool()

    val nodes = mutableSetOf<Node>()
    val links = mutableSetOf<Link>()

    operator fun plusAssign(node: Node) {
        nodes += node
        node.onAttach(this)
    }

    operator fun plusAssign(link: Link) {
        links.filter {
            it.end == link.end
        }.forEach { this -= it }

        links += link
        link.onAttach(this)
    }

    operator fun minusAssign(node: Node) {
        nodes -= node
        node.onDetach(this)
    }

    operator fun minusAssign(link: Link) {
        links -= link
        link.onDetach(this)
    }

    fun acquireNodeId(): Int =
        nodeIds.acquire()

    fun acquirePortId(): Int =
        portIds.acquire()

    fun acquireLinkId(): Int =
        linkIds.acquire()

    fun releaseNodeId(id: Int) {
        nodeIds.release(id)
    }

    fun releasePortId(id: Int) {
        portIds.release(id)
    }

    fun releaseLinkId(id: Int) {
        linkIds.release(id)
    }

    override fun destroy() {
        super.destroy()

        nodes.forEach {
            it.onDetach(this)
        }

        nodeIds.free()
        portIds.free()
        linkIds.free()
    }
}

class IdPool {
    private val ids = BitSet()
    fun acquire(): Int =
        synchronized(ids) {
            ids.nextClearBit(0).also {
                ids.set(it)
            }
        }

    fun release(id: Int) {
        synchronized(ids) {
            ids.clear(id)
        }
    }

    fun free() {
        ids.clear()
    }
}