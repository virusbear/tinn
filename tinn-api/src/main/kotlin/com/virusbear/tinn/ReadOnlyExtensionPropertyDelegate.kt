package com.virusbear.tinn

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ReadOnlyExtensionPropertyDelegate<T, V>(
    private val value: V
): ReadOnlyProperty<T, V> {
    override fun getValue(thisRef: T, property: KProperty<*>): V =
        value
}

fun <T, V> readOnly(value: V): ReadOnlyExtensionPropertyDelegate<T, V> =
    ReadOnlyExtensionPropertyDelegate(value)