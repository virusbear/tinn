package com.virusbear.tinn.nodes

import kotlin.reflect.KProperty

class ReadOnlyPortDelegate<T: Any>(private val port: Port) {
    @Suppress("UNCHECKED_CAST")
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T =
        port.value as T
}