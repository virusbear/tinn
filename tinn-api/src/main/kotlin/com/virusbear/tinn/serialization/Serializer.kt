package com.virusbear.tinn.serialization

import kotlin.reflect.KClass

interface Serializer<T> {
    val type: KClass<*>
    fun Encoder.serialize(value: T): EncodedValue
    fun Decoder.deserialize(value: EncodedValue): T
}

inline fun <reified T> Serializer(noinline serializer: Encoder.(T) -> EncodedValue, noinline deserializer: Decoder.(EncodedValue) -> T): Serializer<T> =
    object: Serializer<T> {
        override val type: KClass<*> = T::class

        override fun Encoder.serialize(value: T): EncodedValue =
            serializer(value)

        override fun Decoder.deserialize(value: EncodedValue): T =
            deserializer(value)
    }