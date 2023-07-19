package com.virusbear.tinn.serialization

import kotlin.reflect.KClass

interface Encoder {
    fun <T: Any> encode(kClass: KClass<T>, value: T): EncodedValue
}

context(Encoder)
val Any?.encoded: EncodedValue
    get() =
        if(this == null) {
            nil
        } else {
            encode(this)
        }

val Encoder.nil: EncodedValue
    get() = EncodedValue.NullValue

context(Encoder)
val Boolean.encoded: EncodedValue
    get() = EncodedValue.BooleanValue(this)

context(Encoder)
val Byte.byte: EncodedValue
    get() = EncodedValue.ByteValue(this)
context(Encoder)
val Int.encoded: EncodedValue
    get() = EncodedValue.IntValue(this)

context(Encoder)
val Long.encoded: EncodedValue
    get() = EncodedValue.LongValue(this)

context(Encoder)
val Double.encoded: EncodedValue
    get() = EncodedValue.DoubleValue(this)

context(Encoder)
val String.encoded: EncodedValue
    get() = EncodedValue.StringValue(this)

context(Encoder)
val ByteArray.encoded: EncodedValue
    get() = EncodedValue.ByteArrayValue(this)

context(Encoder)
val IntArray.encoded: EncodedValue
    get() = EncodedValue.IntArrayValue(this)

context(Encoder)
val LongArray.encoded: EncodedValue
    get() = EncodedValue.LongArrayValue(this)

context(Encoder)
val DoubleArray.encoded: EncodedValue
    get() = EncodedValue.DoubleArrayValue(this)

context(Encoder)
fun <T, R: EncodedValue> List<T>.list(block: (T) -> R): EncodedValue.ListValue<R> =
    list<R> {
        this@list.forEach {
            this += block(it)
        }
    }

fun <T: EncodedValue> Encoder.list(block: MutableList<T>.() -> Unit): EncodedValue.ListValue<T> =
    EncodedValue.ListValue(mutableListOf<T>().apply(block))

fun Encoder.compound(block: (MutableMap<String, EncodedValue>) -> Unit): EncodedValue.CompoundValue =
    EncodedValue.CompoundValue(mutableMapOf<String, EncodedValue>().apply(block))