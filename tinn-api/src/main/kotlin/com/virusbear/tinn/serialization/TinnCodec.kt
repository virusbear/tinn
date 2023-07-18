package com.virusbear.tinn.serialization

import com.virusbear.tinn.extensions.Serializers
import com.virusbear.tinn.registry.Registries
import kotlin.reflect.KClass

class TinnCodec: Codec {
    private val serializers = Registries.Serializers.values()

    override fun <T> encode(value: T): EncodedValue {
        return serializer<T>().serialize(value, this)
    }

    override fun <T> decode(value: EncodedValue): T {
        return serializer<T>().deserialize(value, this)
    }

    private fun <T> serializer(): Serializer<T> =
        serializerOrNull() ?: error("No serializer available")

    private fun <T> serializerOrNull(): Serializer<T>? =
        serializers.filterIsInstance<Serializer<T>>().firstOrNull()
}