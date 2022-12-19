package com.virusbear.tinn.nodes

import com.virusbear.tinn.BaseDestroyable
import org.jgrapht.graph.DirectedAcyclicGraph
import java.util.Stack
import java.util.function.Supplier

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

    private val nodeIds = IdPool()
    private val portIds = IdPool()
    private val linkIds = IdPool()

    val nodes = mutableSetOf<Node>()
    val links = mutableSetOf<Link>()
    private val graph = DirectedAcyclicGraph<Node, Link>({ null }, { null }, false, true)
    fun evaluate() {
        for(node in graph) {
            node.process()

            node.ports.filter { port -> port.direction == PortDirection.Output }.forEach { port ->
                links.filter { link -> link.start == port }.forEach {
                    it.propagate()
                }
            }
        }
    }

    operator fun plusAssign(node: Node) {
        nodes += node
        graph.addVertex(node)
        node.onAttach(this)
    }

    operator fun plusAssign(link: Link) {
        links.filter {
            it.end == link.end
        }.forEach { this -= it }

        links += link
        graph.addEdge(link.start.node, link.end.node, link)
        link.onAttach(this)
    }

    operator fun minusAssign(node: Node) {
        nodes -= node
        graph.removeVertex(node)
        node.onDetach(this)
    }

    operator fun minusAssign(link: Link) {
        links -= link
        graph.removeEdge(link)
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