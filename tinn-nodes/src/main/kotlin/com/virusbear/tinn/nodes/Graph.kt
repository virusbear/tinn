package com.virusbear.tinn.nodes

class Graph<V, E> private constructor(
    private val edges: MutableMap<E, Pair<V, V>>,
    private val inbound: MutableMap<V, MutableSet<E>>,
    private val outbound: MutableMap<V, MutableSet<E>>
) {
    constructor(): this(edges = HashMap(), inbound = HashMap(), outbound = HashMap())

    val edgeCount: Int
        get() = edges.size
    var vertexCount: Int = 0
        private set

    fun edges(): List<E> =
        edges.keys.toList()
    fun vertices(): List<V> =
        (inbound.keys union outbound.keys).toList()

    fun addVertex(vertex: V) {
        if(vertex !in inbound) {
            inbound[vertex] = mutableSetOf()
        }

        if(vertex !in outbound) {
            outbound[vertex] = mutableSetOf()
        }

        vertexCount++
    }
    fun addEdge(edge: E, start: V, end: V) {
        require(start in outbound) { "start not added to graph" }
        require(end in inbound) { "end not added to graph" }

        outbound[start]!!.add(edge)
        inbound[end]!!.add(edge)
        edges[edge] = start to end
    }

    fun removeVertex(vertex: V) {
        var removed = false
        inbound[vertex]?.let {
            it.forEach(edges::remove)
            removed = true
        }
        outbound[vertex]?.let {
            it.forEach(edges::remove)
            removed = true
        }

        if(removed) {
            vertexCount--
        }
    }
    fun removeEdge(edge: E) {
        edges[edge]?.let { (start, end) ->
            edges -= edge

            outbound[start]?.remove(edge)
            inbound[end]?.remove(edge)
        }
    }

    fun indegree(vertex: V): Int =
        inbound[vertex]?.size ?: 0

    fun outdegree(vertex: V): Int =
        outbound[vertex]?.size ?: 0

    fun copyOf(): Graph<V, E> =
        Graph(edges.toMutableMap(), inbound.toMutableMap(), outbound.toMutableMap())

    //TODO: implement heavy optimization
    //1. Avoid copying whole graph
    //2. avoid to many list copy/filter operations
    //3. improve list access performance
    fun topologicalSort(): List<Pair<V, List<E>>> {
        println("Sorting")
        println(edgeCount)
        println(vertexCount)
        val graph = copyOf()
        val result = mutableListOf<Pair<V, List<E>>>()
        val withoutIncoming = (outbound.filterValues { it.isNotEmpty() }.keys subtract inbound.filterValues { it.isNotEmpty() }.keys).toMutableSet()

        while(withoutIncoming.isNotEmpty()) {
            val vertex = withoutIncoming.first().also { withoutIncoming -= it }
            result += vertex to (outbound[vertex]?.toList() ?: emptyList())

            outbound[vertex]?.toList()?.forEach { edge ->
                graph.removeEdge(edge)
                edges[edge]?.second?.let {
                    if(graph.indegree(it) == 0) {
                        withoutIncoming += it
                    }
                }
            }
        }

        if(graph.edgeCount != 0) {
            //error("Graph is not a DAG unable to sort vertices")
        }

        return result
    }
}