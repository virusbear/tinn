package com.virusbear.tinn.serialization

import com.virusbear.tinn.extensions.Serializers
import com.virusbear.tinn.registry.Registries
import kotlin.reflect.KClass

class TinnCodec: Codec {
    private val serializers = Registries.Serializers.values().associateBy { it.type }

    override fun <T: Any> encode(kClass: KClass<T>, value: T): EncodedValue =
        with(serializer(kClass)) {
            serialize(value)
        }

    override fun <T: Any> decode(kClass: KClass<T>, value: EncodedValue): T =
        with(serializer(kClass)) {
            deserialize(value)
        }

    private fun <T: Any> serializer(kClass: KClass<T>): Serializer<T> =
        serializerOrNull(kClass) ?: error("No serializer available for type ${kClass.simpleName}")

    @Suppress("UNCHECKED_CAST")
    private fun <T: Any> serializerOrNull(kClass: KClass<T>): Serializer<T>? =
        serializers[kClass] as? Serializer<T>?
}

inline fun <reified T: Any> Encoder.encode(value: T): EncodedValue =
    encode(T::class, value)

inline fun <reified T: Any> Decoder.decode(value: EncodedValue): T =
    decode(T::class, value)