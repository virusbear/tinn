package com.virusbear.tinn.serialization

import kotlin.reflect.KClass

interface Serializer<T> {
    val type: KClass<*>
    fun Encoder.serialize(value: T)
    fun Decoder.deserialize(): T
}

inline fun <reified T> Serializer(noinline serializer: Encoder.(T) -> Unit, noinline deserializer: Decoder.() -> T): Serializer<T> =
    object: Serializer<T> {
        override val type: KClass<*> = T::class

        override fun Encoder.serialize(value: T) {
            serializer(value)
        }


        override fun Decoder.deserialize(): T =
            deserializer()
    }