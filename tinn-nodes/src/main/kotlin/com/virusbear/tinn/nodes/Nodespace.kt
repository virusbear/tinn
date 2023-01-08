package com.virusbear.tinn.nodes

import com.virusbear.tinn.*
import com.virusbear.tinn.events.NodespaceActivateEvent
import com.virusbear.tinn.Context
import org.jgrapht.graph.DirectedAcyclicGraph

class Nodespace(name: String, val parent: Nodespace? = null): BaseDestroyable(), SceneSavable {
    var name: String = name
        private set
    private val nodeIds = IdPool()
    private val portIds = IdPool()
    private val linkIds = IdPool()

    private val graph = DirectedAcyclicGraph<Node, Link>({ null }, { null }, false, true)
    //Graph.vertexSet returns reference to internal vertex storage. no need to query this on every access
    val nodes: Set<Node> = graph.vertexSet()
    val links: Set<Link> = graph.edgeSet()

    fun evaluate(context: Context) {
        //TODO: Skip Nodes that do not link to any other node.
        //TODO: how to identify start and end of graph
        for(node in graph) {
            node.process(context)

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

    fun portByIdOrNull(portId: Int): Port? =
        nodes.flatMap { it.ports }.firstOrNull { it.id == portId }

    fun linksForPort(port: Port): List<Link> =
        when(port.direction) {
            PortDirection.Input -> links.filter { it.end == port }
            PortDirection.Output -> links.filter { it.start == port }
        }

    operator fun plusAssign(node: Node) {
        graph.addVertex(node)
        if(node.id != -1) {
            nodeIds.acquire(node.id)
        }
        node.ports.filter { it.id != -1 }.forEach {
            portIds.acquire(it.id)
        }
        node.onAttach(this)
    }

    operator fun plusAssign(link: Link) {
        links.filter {
            it.end == link.end
        }.forEach { this -= it }

        if(link.id != -1) {
            linkIds.acquire(link.id)
        }

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

    fun clear() {
        //Nodes are copied to avoid ConcurrentModificationException due to -= removing vertex from graph
        nodes.toList().forEach {
            this -= it
        }
    }

    override fun save(writer: SceneWriter) {
        writer.write("version", SCENE_VERSION)
        writer.write("name", name)
        writer.writeList("nodeDefinitions", nodes) {
            write("_node_category", it.identifier.category.toString())
            write("_node_name", it.identifier.name)
            write("_node_id", it.id)
        }
        nodes.forEach {
            writer.writeCompound("_node_${it.id}", it) {
                it.save(this)
            }
        }
        writer.writeList("links", links) {
            write("id", it.id)
            write("start", it.start.id)
            write("end", it.end.id)
        }
    }

    override fun load(reader: SceneReader, context: Context) {
        val version = reader.string("version")
        require(SCENE_VERSION.version >= version.version) { "Unsupported file version. Unable to load Nodespace" }
        clear()
        name = reader.string("name")

        val newContext = context + NodespaceContextElement(this)

        //TODO: Change loading order
        // 1. create all nodes in nodespace
        // 2. load all individual nodes from reader
        // 3. attach all nodes to nodespace
        // 4. create links
        //
        // this could ensure correct loading behavior of nodes referencing other nodes.

        val nodeConfigReader = reader.compound("nodeConfigurations")

        reader.list("nodeDefinitions") {
            val nodeCategory = string("_node_category")
            val nodeName = string("_node_name")
            val nodeId = int("_node_id")
            val nodeIdentifier: NodeIdentifier? = NodeManager.resolveNodeIdentifier(nodeCategory, nodeName)
            nodeId to nodeIdentifier?.new(newContext)
        }.filter { (_, node) -> node != null }.map { (id, node) -> id to node!! }.onEach { (nodeId, node) ->
            node.id = nodeId
            graph.addVertex(node)
        }.forEach { (nodeId, node) ->
            node.load(nodeConfigReader.compound("_node_$nodeId"), newContext)
            this@Nodespace += node
        }
        reader.list("links") {
            val id = int("id")
            val startPortId = int("start")
            val endPortId = int("end")
            val startPort = this@Nodespace.portByIdOrNull(startPortId)
            val endPort = this@Nodespace.portByIdOrNull(endPortId)

            if(startPort != null && endPort != null) {
                Link(id, startPort, endPort)
            } else {
                null
            }
        }
    }

    override fun destroy() {
        super.destroy()

        clear()

        nodeIds.free()
        portIds.free()
        linkIds.free()
    }

    companion object {
        private const val SCENE_VERSION = "0.0.1"
    }
}

class NodespaceContextElement(
    val nodespace: Nodespace
): AbstractContextElement(NodespaceContextElement) {
    companion object Key: Context.Key<NodespaceContextElement>

    override fun toString(): String =
        "Nodespace(${nodespace.name})"
}