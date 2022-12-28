package com.virusbear.tinn.nodes

import kotlin.reflect.KClass

abstract class DynamicPortNode(
    override val name: String,
    deletable: Boolean = true,
    val dynamicInputsAllowed: Boolean = true,
    val dynamicOutputsAllowed: Boolean = true
): BaseNode(name, deletable) {
    private val _dynamicPorts = HashSet<Port>()

    val dynamicPorts: List<Port>
        get() = _dynamicPorts.toList()

    open fun <T: Any?> addPort(
        direction: PortDirection,
        name: String,
        type: KClass<*>,
        default: T?
    ): Port = port(
        direction,
        name,
        type,
        default
    ).also {
        when(direction) {
            PortDirection.Input -> if(!dynamicInputsAllowed) return@also
            PortDirection.Output -> if(!dynamicOutputsAllowed) return@also
        }
        it.onAttach(nodespace)
        _dynamicPorts += it
    }

    open fun removePort(
        port: Port
    ) {
        this -= port
        _dynamicPorts -= port
    }
}