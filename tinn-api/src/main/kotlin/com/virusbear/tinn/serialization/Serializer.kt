package com.virusbear.tinn.serialization

interface Serializer<T> {
    fun serialize(value: T, encoder: Encoder): EncodedValue
    fun deserialize(value: EncodedValue, decoder: Decoder): T
}