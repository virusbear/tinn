package com.virusbear.tinn.nodes

import com.virusbear.tinn.EmptyContext

class Link(
    val start: Port,
    val end: Port
) {
    internal constructor(id: Int, start: Port, end: Port): this(start, end) {
        this.id = id
    }

    var id: Int = -1
    private set

    private var calculated = false

    init {
        require(end.type.java.isAssignableFrom(start.type.java)) { "Unable to link ports with different types" }
    }

    fun onAttach(nodespace: Nodespace) {
        if(id == -1)
            id = nodespace.acquireLinkId()

        end.link = this
    }

    fun onDetach(nodespace: Nodespace) {
        nodespace.releaseLinkId(id)
        id = -1

        end.reset()
        end.link = null
    }

    fun reset() {
        calculated = false
    }

    fun propagate() {
        if(!calculated) {
            start.node.process(EmptyContext)
            calculated = true
        }
        end.value = start.value
    }
}