package com.virusbear.tinn.serialization

sealed class EncodedValue {
    object NullValue: EncodedValue()
    data class BooleanValue(val value: Boolean) : EncodedValue()
    data class ByteValue(val value: Byte) : EncodedValue()
    data class IntValue(val value: Int) : EncodedValue()
    data class LongValue(val value: Long) : EncodedValue()
    data class DoubleValue(val value: Double) : EncodedValue()
    data class StringValue(val value: String) : EncodedValue()
    data class ByteArrayValue(val value: ByteArray) : EncodedValue()
    data class IntArrayValue(val value: IntArray) : EncodedValue()
    data class LongArrayValue(val value: LongArray) : EncodedValue()
    data class DoubleArrayValue(val value: DoubleArray) : EncodedValue()
    data class ListValue<T: EncodedValue>(val value: List<T>) : EncodedValue(), List<T> by value
    data class CompoundValue(val value: Map<String, EncodedValue>) : EncodedValue(), Map<String, EncodedValue> by value {
        override fun get(key: String): EncodedValue =
            value[key] ?: NullValue
    }
}