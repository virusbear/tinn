package com.virusbear.tinn.registry

import com.virusbear.tinn.ReadOnlyExtensionPropertyDelegate
import kotlin.properties.ReadOnlyProperty

object Registries {
    inline fun <reified T: Any> registry(): ReadOnlyProperty<Registries, Registry<T>> =
        ReadOnlyExtensionPropertyDelegate(Registry())
}