package com.virusbear.tinn.serialization

import kotlin.reflect.KClass

interface Decoder {
    fun <T: Any> decode(kClass: KClass<T>): T
    val boolean: Boolean
    val booleanOrNull: Boolean?
    val byte: Byte
    val byteOrNull: Byte?
    val short: Short
    val shortOrNull: Short?
    val int: Int
    val intOrNull: Int?
    val long: Long
    val longOrNull: Long?
    val float: Float
    val floatOrNull: Float?
    val double: Double
    val doubleOrNull: Double?
    val string: String
    val stringOrNull: String?
    val byteArray: ByteArray
    val byteArrayOrNull: ByteArray?
    fun <T> list(block: Decoder.() -> T): List<T>
    fun <T> compound(block: CompoundDecoder.() -> T): T
}

inline fun <reified T: Any> Decoder.decode(): T =
    decode(T::class)