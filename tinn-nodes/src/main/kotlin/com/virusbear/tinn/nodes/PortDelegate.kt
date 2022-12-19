package com.virusbear.tinn.nodes

import kotlin.reflect.KProperty

class PortDelegate<T: Any?>(private val port: Port) {
    @Suppress("UNCHECKED_CAST")
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T =
        (port.value as? T) ?: port.default as T

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        port.value = value
    }
}