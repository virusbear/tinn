package com.virusbear.tinn.nodes

import kotlin.reflect.KClass

class Port(
    val id: Int,
    val node: Node,
    val direction: PortDirection,
    val name: String,
    val type: KClass<*>,
    val default: Any? = null
) {
    init {
        if(default != null)
            require(default::class == type) { "Default value does not match port type" }
    }
    var value: Any? = default
}