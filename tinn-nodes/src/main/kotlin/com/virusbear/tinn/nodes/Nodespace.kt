package com.virusbear.tinn.nodes

import com.virusbear.tinn.BaseDestroyable
import com.virusbear.tinn.EventBus
import com.virusbear.tinn.events.NodespaceActivateEvent
import org.jgrapht.graph.DirectedAcyclicGraph

class Nodespace(val name: String): BaseDestroyable() {
    private val nodeIds = IdPool()
    private val portIds = IdPool()
    private val linkIds = IdPool()

    private val graph = DirectedAcyclicGraph<Node, Link>({ null }, { null }, false, true)
    //Graph.vertexSet returns reference to internal vertex storage. no need to query this on every access
    val nodes: Set<Node> = graph.vertexSet()
    val links: Set<Link> = graph.edgeSet()

    fun evaluate() {
        for(node in graph) {
            node.process()

            node.ports.filter { port -> port.direction == PortDirection.Output }.forEach { port ->
                graph.edgesOf(node).filter { link -> link.start == port }.forEach {
                    it.propagate()
                }
            }
        }
    }

    fun makeCurrent() {
        EventBus.publish(NodespaceActivateEvent(this))
    }

    fun nodeByIdOrNull(nodeId: Int): Node? =
        nodes.firstOrNull { it.id == nodeId }

    fun linkByIdOrNull(linkId: Int): Link? =
        links.firstOrNull { it.id == linkId }

    fun linksForPort(port: Port): List<Link> =
        when(port.direction) {
            PortDirection.Input -> links.filter { it.end == port }
            PortDirection.Output -> links.filter { it.start == port }
        }

    operator fun plusAssign(node: Node) {
        graph.addVertex(node)
        node.onAttach(this)
    }

    operator fun plusAssign(link: Link) {
        links.filter {
            it.end == link.end
        }.forEach { this -= it }

        graph.addEdge(link.start.node, link.end.node, link)
        link.onAttach(this)
    }

    operator fun minusAssign(node: Node) {
        graph.edgesOf(node).forEach {
            this -= it
        }
        graph.removeVertex(node)

        node.onDetach(this)
    }

    operator fun minusAssign(link: Link) {
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

        //Nodes are copied to avoid ConcurrentModificationException due to -= removing vertex from graph
        nodes.toList().forEach {
            this -= it
        }

        nodeIds.free()
        portIds.free()
        linkIds.free()
    }
}