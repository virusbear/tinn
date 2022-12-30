package com.virusbear.tinn.nodes

import com.virusbear.tinn.*
import com.virusbear.tinn.events.NodespaceActivateEvent
import org.jgrapht.graph.DirectedAcyclicGraph

class Nodespace(val name: String): BaseDestroyable() {
    private constructor(name: String, nodes: Iterable<Node>, links: Iterable<Link>): this(name) {
        nodes.forEach {
            graph.addVertex(it)
            nodeIds.acquire(it.id)
            it.ports.forEach {
                portIds.acquire(it.id)
            }
            it.onAttach(this)
        }

        links.forEach {
            graph.addEdge(it.start.node, it.end.node, it)
            linkIds.acquire(it.id)
            it.onAttach(this)
        }
    }

    private val nodeIds = IdPool()
    private val portIds = IdPool()
    private val linkIds = IdPool()

    private val graph = DirectedAcyclicGraph<Node, Link>({ null }, { null }, false, true)
    //Graph.vertexSet returns reference to internal vertex storage. no need to query this on every access
    val nodes: Set<Node> = graph.vertexSet()
    val links: Set<Link> = graph.edgeSet()

    fun evaluate() {
        //TODO: Skip Nodes that do not link to any other node.
        //TODO: how to identify start and end of graph
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

    fun save(writer: SceneWriter) {
        writer.write("version", SCENE_VERSION)
        writer.write("name", name)
        writer.writeList("nodes", nodes) {
            write("_node_category", it.identifier.category.toString())
            write("_node_name", it.identifier.name)
            it.save(this)
        }
        writer.writeList("links", links) {
            write("id", it.id)
            write("start", it.start.id)
            write("end", it.end.id)
        }
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

    companion object {
        private const val SCENE_VERSION = "0.0.1"

        fun load(reader: SceneReader): Nodespace {
            val version = reader.string("version")
            require(SCENE_VERSION.version >= version.version) { "Unsupported file version. Unable to load Nodespace" }
            val name = reader.string("name")
            val nodes = reader.list("nodes") {
                val nodeCategory = string("_node_category")
                val nodeName = string("_node_name")
                val nodeIdentifier: NodeIdentifier? = NodeManager.resolveNodeIdentifier(nodeCategory, nodeName)
                nodeIdentifier?.new()?.also { it.load(this) }
            }.filterNotNull()
            val allPorts = nodes.flatMap { it.ports }
            val links = reader.list("links") {
                val id = reader.int("id")
                val startPortId = reader.int("start")
                val endPortId = reader.int("end")
                val startPort = allPorts.firstOrNull { it.id == startPortId }
                val endPort = allPorts.firstOrNull { it.id == endPortId }
                if(startPort != null && endPort != null) {
                    Link(id, startPort, endPort)
                } else {
                    null
                }
            }.filterNotNull()

            return Nodespace(name, nodes, links)
        }
    }
}