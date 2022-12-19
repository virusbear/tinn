package com.virusbear.tinn.nodes

class Link(
    val start: Port,
    val end: Port
) {
    var id: Int = -1
    private set

    init {
        require(start.type == end.type) { "Unable to link ports with different types" }
    }

    fun onAttach(nodespace: Nodespace) {
        id = nodespace.acquireLinkId()
    }

    fun onDetach(nodespace: Nodespace) {
        nodespace.releaseLinkId(id)
        id = -1
    }
}