package com.virusbear.tinn.ui

import com.virusbear.tinn.ReadOnlyExtensionPropertyDelegate
import com.virusbear.tinn.registry.Registries
import com.virusbear.tinn.registry.Registries.registry
import com.virusbear.tinn.registry.Registry

object Theme {
    private val theme = mutableMapOf<ThemeVariable<*>, Any>()

    fun <T: Any> themeVariable(default: T): ReadOnlyExtensionPropertyDelegate<Theme, ThemeVariable<T>> =
        ReadOnlyExtensionPropertyDelegate(ThemeVariable(default))

    class ThemeVariable<T: Any>(
        private val default: T
    ) {
        @Suppress("UNCHECKED_CAST")
        operator fun invoke(): T =
            theme[this] as? T? ?: default

        operator fun invoke(value: T) {
            theme[this] = value
        }
    }
}