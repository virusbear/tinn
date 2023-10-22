package com.virusbear.tinn.serialization

import kotlin.reflect.KClass

interface BinaryFormat: Format {
    fun <T> encodeToByteArray(value: T): ByteArray
    fun <T: Any> decodeFromByteArray(kClass: KClass<T>, value: ByteArray): T
}

inline fun <reified T: Any> BinaryFormat.decodeFromByteArray(value: ByteArray): T =
    decodeFromByteArray(T::class, value)