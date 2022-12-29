package com.virusbear.tinn.nodes

import kotlin.reflect.KClass

//TODO: add mandatory flag to better visualize optional and required ports
class Port(
    val node: Node,
    val direction: PortDirection,
    val name: String,
    val type: KClass<*>,
    val default: Any? = null
) {
    init {
        if(default != null)
            require(type.java.isAssignableFrom(default::class.java)) { "Default value does not match port type" }
    }

    var id: Int = -1
    private set

    fun onAttach(nodespace: Nodespace) {
        id = nodespace.acquirePortId()
    }
    fun onDetach(nodespace: Nodespace) {
        nodespace.releasePortId(id)
        id = -1
    }

    fun reset() {
        value = default
    }

    var value: Any? = default
}