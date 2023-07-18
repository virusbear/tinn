package com.virusbear.tinn.serialization

import com.virusbear.tinn.color.Color
import com.virusbear.tinn.extensions.Serializers
import com.virusbear.tinn.registry.Registries

object ColorSerializer: Serializer<Color> {
    override fun serialize(value: Color, encoder: Encoder): EncodedValue =
        compound {
            it["r"] = encoder.encode(value.r)
            it["g"] = encoder.encode(value.g)
            it["b"] = encoder.encode(value.b)
            it["a"] = encoder.encode(value.a)
        }

    override fun deserialize(value: EncodedValue, decoder: Decoder): Color {
        require(value is EncodedValue.CompoundValue)

        return Color(
            decoder.decode<Double>(value["r"] ?: error("Color requires r component")),
            decoder.decode<Double>(value["g"] ?: error("Color requires g component")),
            decoder.decode<Double>(value["b"] ?: error("Color requires b component")),
            decoder.decode<Double>(value["a"] ?: error("Color requires a component"))
        )
    }
}

fun main() {
    Registries.Serializers.register("tinn:color", ColorSerializer)
    Registries.Serializers.register("tinn:double", DoubleSerializer)

    println(TinnCodec().encode(Color.WHITE))
}