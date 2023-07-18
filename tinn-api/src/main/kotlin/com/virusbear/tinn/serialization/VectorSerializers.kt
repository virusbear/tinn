package com.virusbear.tinn.serialization

import com.virusbear.tinn.math.*

object Vec2Serializer: Serializer<Vec2> {
    override fun serialize(value: Vec2, encoder: Encoder): EncodedValue =
        compound {
            it["x"] = encoder.encode(value.x)
            it["y"] = encoder.encode(value.y)
        }

    override fun deserialize(value: EncodedValue, decoder: Decoder): Vec2 {
        require(value is EncodedValue.CompoundValue)

        return Vec2(
            decoder.decode(value["x"] ?: error("Vec2 requires x component")),
            decoder.decode(value["y"] ?: error("Vec2 requires y component"))
        )
    }
}

object IVec2Serializer: Serializer<IVec2> {
    override fun serialize(value: IVec2, encoder: Encoder): EncodedValue =
        compound {
            it["x"] = encoder.encode(value.x)
            it["y"] = encoder.encode(value.y)
        }

    override fun deserialize(value: EncodedValue, decoder: Decoder): IVec2 {
        require(value is EncodedValue.CompoundValue)

        return IVec2(
            decoder.decode(value["x"] ?: error("IVec2 requires x component")),
            decoder.decode(value["y"] ?: error("IVec2 requires y component"))
        )
    }
}

object Vec3Serializer: Serializer<Vec3> {
    override fun serialize(value: Vec3, encoder: Encoder): EncodedValue =
        compound {
            it["x"] = encoder.encode(value.x)
            it["y"] = encoder.encode(value.y)
            it["z"] = encoder.encode(value.z)
        }

    override fun deserialize(value: EncodedValue, decoder: Decoder): Vec3 {
        require(value is EncodedValue.CompoundValue)

        return Vec3(
            decoder.decode(value["x"] ?: error("Vec3 requires x component")),
            decoder.decode(value["y"] ?: error("Vec3 requires y component")),
            decoder.decode(value["z"] ?: error("Vec3 requires z component"))
        )
    }
}

object IVec3Serializer: Serializer<IVec3> {
    override fun serialize(value: IVec3, encoder: Encoder): EncodedValue =
        compound {
            it["x"] = encoder.encode(value.x)
            it["y"] = encoder.encode(value.y)
            it["z"] = encoder.encode(value.z)
        }

    override fun deserialize(value: EncodedValue, decoder: Decoder): IVec3 {
        require(value is EncodedValue.CompoundValue)

        return IVec3(
            decoder.decode(value["x"] ?: error("IVec3 requires x component")),
            decoder.decode(value["y"] ?: error("IVec3 requires y component")),
            decoder.decode(value["z"] ?: error("IVec3 requires z component"))
        )
    }
}

object Vec4Serializer: Serializer<Vec4> {
    override fun serialize(value: Vec4, encoder: Encoder): EncodedValue =
        compound {
            it["x"] = encoder.encode(value.x)
            it["y"] = encoder.encode(value.y)
            it["z"] = encoder.encode(value.z)
            it["w"] = encoder.encode(value.w)
        }

    override fun deserialize(value: EncodedValue, decoder: Decoder): Vec4 {
        require(value is EncodedValue.CompoundValue)

        return Vec4(
            decoder.decode(value["x"] ?: error("Vec4 requires x component")),
            decoder.decode(value["y"] ?: error("Vec4 requires y component")),
            decoder.decode(value["z"] ?: error("Vec4 requires z component")),
            decoder.decode(value["w"] ?: error("Vec4 requires w component"))
        )
    }
}

object IVec4Serializer: Serializer<IVec4> {
    override fun serialize(value: IVec4, encoder: Encoder): EncodedValue =
        compound {
            it["x"] = encoder.encode(value.x)
            it["y"] = encoder.encode(value.y)
            it["z"] = encoder.encode(value.z)
            it["w"] = encoder.encode(value.w)
        }

    override fun deserialize(value: EncodedValue, decoder: Decoder): IVec4 {
        require(value is EncodedValue.CompoundValue)

        return IVec4(
            decoder.decode(value["x"] ?: error("IVec4 requires x component")),
            decoder.decode(value["y"] ?: error("IVec4 requires y component")),
            decoder.decode(value["z"] ?: error("IVec4 requires z component")),
            decoder.decode(value["w"] ?: error("IVec4 requires w component"))
        )
    }
}