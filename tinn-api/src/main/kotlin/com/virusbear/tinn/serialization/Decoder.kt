package com.virusbear.tinn.serialization

import kotlin.reflect.KClass

interface Decoder {
    fun <T: Any> decode(kClass: KClass<T>, value: EncodedValue): T
}

context(Decoder)
val EncodedValue.boolean: Boolean
    get() = booleanOrNull ?: wrongValueType(EncodedValue.BooleanValue::class, this::class)

context(Decoder)
val EncodedValue.booleanOrNull: Boolean?
    get() = (this as? EncodedValue.BooleanValue)?.value

context(Decoder)
val EncodedValue.byte: Byte
    get() = byteOrNull ?: wrongValueType(EncodedValue.ByteValue::class, this::class)

context(Decoder)
val EncodedValue.byteOrNull: Byte?
    get() = (this as? EncodedValue.ByteValue)?.value

context(Decoder)
val EncodedValue.int: Int
    get() = intOrNull ?: wrongValueType(EncodedValue.IntValue::class, this::class)

context(Decoder)
val EncodedValue.intOrNull: Int?
    get() = (this as? EncodedValue.IntValue)?.value

context(Decoder)
val EncodedValue.long: Long
    get() = longOrNull ?: wrongValueType(EncodedValue.LongValue::class, this::class)

context(Decoder)
val EncodedValue.longOrNull: Long?
    get() = (this as? EncodedValue.LongValue)?.value

context(Decoder)
val EncodedValue.double: Double
    get() = doubleOrNull ?: wrongValueType(EncodedValue.DoubleValue::class, this::class)

context(Decoder)
val EncodedValue.doubleOrNull: Double?
    get() = (this as? EncodedValue.DoubleValue)?.value

context(Decoder)
val EncodedValue.string: String
    get() = stringOrNull ?: wrongValueType(EncodedValue.StringValue::class, this::class)

context(Decoder)
val EncodedValue.stringOrNull: String?
    get() = (this as? EncodedValue.StringValue)?.value

context(Decoder)
val EncodedValue.byteArray: ByteArray
    get() = byteArrayOrNull ?: wrongValueType(EncodedValue.ByteArrayValue::class, this::class)

context(Decoder)
val EncodedValue.byteArrayOrNull: ByteArray?
    get() = (this as? EncodedValue.ByteArrayValue)?.value

context(Decoder)
val EncodedValue.intArray: IntArray
    get() = intArrayOrNull ?: wrongValueType(EncodedValue.IntArrayValue::class, this::class)

context(Decoder)
val EncodedValue.intArrayOrNull: IntArray?
    get() = (this as? EncodedValue.IntArrayValue)?.value

context(Decoder)
val EncodedValue.longArray: LongArray
    get() = longArrayOrNull ?: wrongValueType(EncodedValue.LongArrayValue::class, this::class)

context(Decoder)
val EncodedValue.longArrayOrNull: LongArray?
    get() = (this as? EncodedValue.LongArrayValue)?.value

context(Decoder)
val EncodedValue.doubleArray: DoubleArray
    get() = doubleArrayOrNull ?: wrongValueType(EncodedValue.DoubleArrayValue::class, this::class)

context(Decoder)
val EncodedValue.doubleArrayOrNull: DoubleArray?
    get() = (this as? EncodedValue.DoubleArrayValue)?.value

context(Decoder)
inline fun <reified T: Any> EncodedValue.list(): List<T> =
    list(T::class)

context(Decoder)
fun <T: Any> EncodedValue.list(kClass: KClass<T>): List<T> =
    listOrNull(kClass) ?: wrongValueType(EncodedValue.ListValue::class, this::class)

context(Decoder)
inline fun <reified T: Any> EncodedValue.listOrNull(): List<T>? =
    listOrNull(T::class)

context(Decoder)
fun <T: Any> EncodedValue.listOrNull(kClass: KClass<T>): List<T>? =
    (this as? EncodedValue.ListValue<*>)?.value?.map {
        decode(kClass, it)
    }

context(Decoder)
fun <T> EncodedValue.compound(block: (EncodedValue.CompoundValue) -> T): T =
    compoundOrNull(block) ?: wrongValueType(EncodedValue.CompoundValue::class, this::class)

context(Decoder)
fun <T> EncodedValue.compoundOrNull(block: (EncodedValue.CompoundValue) -> T): T? =
    (this as? EncodedValue.CompoundValue)?.run(block)

private fun wrongValueType(expected: KClass<*>, actual: KClass<*>): Nothing {
    val expectedValueName = expected.simpleName?.removeSuffix("Value")
    val actualValueName = actual.simpleName?.removeSuffix("Value")
    error("expected $expectedValueName but got ${actualValueName ?: "Unknown"}")
}