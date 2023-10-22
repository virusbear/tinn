package com.virusbear.tinn.serialization

import kotlin.reflect.KClass

interface CompoundDecoder {
    fun <T: Any> decode(key: String, kClass: KClass<T>): T
    fun boolean(key: String): Boolean
    fun booleanOrNull(key: String): Boolean?
    fun byte(key: String): Byte
    fun byteOrNull(key: String): Byte?
    fun short(key: String): Short
    fun shortOrNull(key: String): Short?
    fun int(key: String): Int
    fun intOrNull(key: String): Int?
    fun long(key: String): Long
    fun longOrNull(key: String): Long?
    fun float(key: String): Float
    fun floatOrNull(key: String): Float?
    fun double(key: String): Double
    fun doubleOrNull(key: String): Double?
    fun string(key: String): String
    fun stringOrNull(key: String): String?
    fun byteArray(key: String): ByteArray
    fun byteArrayOrNull(key: String): ByteArray?
    fun <T> list(key: String, block: Decoder.() -> T): List<T>
    fun <T> compound(block: CompoundDecoder.() -> T): T
}

inline fun <reified T: Any> CompoundDecoder.decode(key: String): T =
    decode(key, T::class)