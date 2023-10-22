package com.virusbear.tinn.serialization

import kotlin.reflect.KClass

interface TextFormat: Format {
    fun <T> encodeToString(value: T): String
    fun <T: Any> decodeFromString(kClass: KClass<T>, value: String): T
}

inline fun <reified T: Any> TextFormat.decodeFromString(value: String): T =
    decodeFromString(T::class, value)