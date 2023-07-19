package com.virusbear.tinn.serialization

import com.virusbear.tinn.math.*
import com.virusbear.tinn.registry.Registry

val Vec2Serializer = Serializer(
    serializer = { value ->
        compound {
            it["x"] = value.x.encoded
            it["y"] = value.y.encoded
        }
    },
    deserializer = { value ->
        value.compound {
            Vec2(
                it["x"].double,
                it["y"].double
            )
        }
    }
)

val IVec2Serializer = Serializer(
    serializer = { value ->
        compound {
            it["x"] = value.x.encoded
            it["y"] = value.y.encoded
        }
    },
    deserializer = { value ->
        value.compound {
            IVec2(
                it["x"].int,
                it["y"].int
            )
        }
    }
)

val Vec3Serializer = Serializer(
    serializer = { value ->
        compound {
            it["x"] = value.x.encoded
            it["y"] = value.y.encoded
            it["z"] = value.z.encoded
        }
    },
    deserializer = { value ->
        value.compound {
            Vec3(
                it["x"].double,
                it["y"].double,
                it["z"].double
            )
        }
    }
)

val IVec3Serializer = Serializer(
    serializer = { value ->
        compound {
            it["x"] = value.x.encoded
            it["y"] = value.y.encoded
            it["z"] = value.z.encoded
        }
    },
    deserializer = { value ->
        value.compound {
            IVec3(
                it["x"].int,
                it["y"].int,
                it["z"].int
            )
        }
    }
)

val Vec4Serializer = Serializer(
    serializer = { value ->
        compound {
            it["x"] = value.x.encoded
            it["y"] = value.y.encoded
            it["z"] = value.z.encoded
            it["w"] = value.w.encoded
        }
    },
    deserializer = { value ->
        value.compound {
            Vec4(
                it["x"].double,
                it["y"].double,
                it["z"].double,
                it["w"].double
            )
        }
    }
)

val IVec4Serializer = Serializer(
    serializer = { value ->
        compound {
            it["x"] = value.x.encoded
            it["y"] = value.y.encoded
            it["z"] = value.z.encoded
            it["w"] = value.w.encoded
        }
    },
    deserializer = { value ->
        value.compound {
            IVec4(
                it["x"].int,
                it["y"].int,
                it["z"].int,
                it["w"].int
            )
        }
    }
)

internal fun Registry<Serializer<*>>.registerVectorSerializers() {
    register("tinn:vec2", Vec2Serializer)
    register("tinn:ivec2", IVec2Serializer)
    register("tinn:vec3", Vec3Serializer)
    register("tinn:ivec3", IVec3Serializer)
    register("tinn:vec4", Vec4Serializer)
    register("tinn:ivec4", IVec4Serializer)
}