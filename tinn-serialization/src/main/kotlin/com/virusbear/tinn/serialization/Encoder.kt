package com.virusbear.tinn.serialization

import kotlin.reflect.KClass

interface Encoder {
    fun <T> encode(value: T)
    fun boolean(value: Boolean)
    fun byte(value: Byte)
    fun short(value: Short)
    fun int(value: Int)
    fun long(value: Long)
    fun float(value: Float)
    fun double(value: Double)
    fun string(value: String)
    fun byteArray(value: ByteArray)
    fun <T> list(value: List<T>, block: Encoder.() -> Unit)
    fun compound(block: CompoundEncoder.() -> Unit)
}