package com.virusbear.tinn.ui.theme

import com.virusbear.tinn.ReadOnlyExtensionPropertyDelegate
import com.virusbear.tinn.registry.Registries
import com.virusbear.tinn.ui.ThemeVariables
import java.io.File

object Theme {
    //TODO: add loading and saving possibilities to Themes
    //TODO: how to serialize and deserialize ThemeVariable types?

    private val theme = mutableMapOf<ThemeVariable<*>, Any>()

    fun save(file: File) {
        Registries.ThemeVariables.entries.map { (id, variable) ->
            "$id" to theme[variable]
        }.filter { (_, variable) -> variable != null }
    }

    fun load(file: File) {

    }

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