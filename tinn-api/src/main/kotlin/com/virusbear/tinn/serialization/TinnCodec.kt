package com.virusbear.tinn.serialization

import com.virusbear.tinn.extensions.Serializers
import com.virusbear.tinn.registry.Registries
import kotlin.reflect.KClass

class TinnCodec: Codec {
    private val serializers = Registries.Serializers.values().toMap()

    override fun <T: Any> encode(kClass: KClass<T>, value: T): EncodedValue {
        return serializer(kClass).serialize(value, this)
    }

    override fun <T: Any> decode(kClass: KClass<T>, value: EncodedValue): T {
        return serializer(kClass).deserialize(value, this)
    }

    private fun <T: Any> serializer(kClass: KClass<T>): Serializer<T> =
        serializerOrNull(kClass) ?: error("No serializer available")

    @Suppress("UNCHECKED_CAST")
    private fun <T: Any> serializerOrNull(kClass: KClass<T>): Serializer<T>? =
        serializers[kClass] as? Serializer<T>?
}

inline fun <reified T: Any> Encoder.encode(value: T): EncodedValue =
    encode(T::class, value)

inline fun <reified T: Any> Decoder.decode(value: EncodedValue): T =
    decode(T::class, value)