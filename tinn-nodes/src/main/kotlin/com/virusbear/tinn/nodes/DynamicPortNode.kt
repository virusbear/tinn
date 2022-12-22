package com.virusbear.tinn.nodes

import kotlin.reflect.KClass

abstract class DynamicPortNode(
    override val name: String
): BaseNode(name) {
    private val _dynamicPorts = HashSet<Port>()

    val dynamicPorts: List<Port>
        get() = _dynamicPorts.toList()

    fun <T: Any?> addPort(
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
        it.onAttach(nodespace)
        _dynamicPorts += it
    }

    fun removePort(
        port: Port
    ) {
        this -= port
        _dynamicPorts -= port
    }
}