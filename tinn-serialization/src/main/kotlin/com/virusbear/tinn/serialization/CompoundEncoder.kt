package com.virusbear.tinn.serialization

import kotlin.reflect.KClass

interface CompoundEncoder {
    fun <T: Any> encode(key: String, kClass: KClass<T>, value: T)
    fun boolean(key: String, value: Boolean)
    fun byte(key: String, value: Byte)
    fun short(key: String, value: Short)
    fun int(key: String, value: Int)
    fun long(key: String, value: Long)
    fun float(key: String, value: Float)
    fun double(key: String, value: Double)
    fun string(key: String, value: String)
    fun byteArray(key: String, value: ByteArray)
    fun <T> list(key: String, value: List<T>, block: Encoder.(T) -> Unit)
    fun compound(key: String, block: CompoundEncoder.() -> Unit)
}

inline fun <reified T: Any> CompoundEncoder.encode(key: String, value: T) {
    encode(key, T::class, value)
}