package com.virusbear.tinn.nodes

import kotlin.reflect.KClass
import kotlin.reflect.KProperty

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

    fun onAttach(nodespace: Nodespace) {
        if(id == -1)
            id = nodespace.acquirePortId()
    }
    fun onDetach(nodespace: Nodespace) {
        nodespace.releasePortId(id)
        id = -1
    }

    inline operator fun <reified T: Any?> getValue(thisRef: Any?, property: KProperty<*>): T {
        require(T::class == type) { "Invalid type. Unable to delegate value" }

        return (value as? T) ?: default as T
    }

    inline operator fun <reified T: Any?> setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        require(T::class == type) { "Invalid type. Unable to delegate value" }

        this.value = value
    }

    fun reset() {
        value = default
    }

    var value: Any? = default
}