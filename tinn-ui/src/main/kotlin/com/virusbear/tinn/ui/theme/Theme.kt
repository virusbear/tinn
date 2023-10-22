package com.virusbear.tinn.ui.theme

import com.virusbear.tinn.ReadOnlyExtensionPropertyDelegate
import java.io.File
import kotlin.reflect.KClass

object Theme {
    //TODO: add loading and saving possibilities to Themes
    //TODO: how to serialize and deserialize ThemeVariable types?

    private val theme = mutableMapOf<ThemeVariable<*>, Any>()

    fun save(file: File) {

    }

    fun load(file: File) {

    }

    inline fun <reified T: Any> themeVariable(default: T): ReadOnlyExtensionPropertyDelegate<Theme, ThemeVariable<T>> =
        ReadOnlyExtensionPropertyDelegate(ThemeVariable(default, T::class))

    class ThemeVariable<T: Any>(
        private val default: T,
        internal val type: KClass<T>
    ) {
        @Suppress("UNCHECKED_CAST")
        operator fun invoke(): T =
            theme[this] as? T? ?: default

        operator fun invoke(value: T) {
            theme[this] = value
        }
    }
}