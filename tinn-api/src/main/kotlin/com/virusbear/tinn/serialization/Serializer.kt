package com.virusbear.tinn.serialization

import kotlin.reflect.KClass

interface Serializer<T> {
    val type: KClass<*>
    fun serialize(value: T, encoder: Encoder): EncodedValue
    fun deserialize(value: EncodedValue, decoder: Decoder): T
}