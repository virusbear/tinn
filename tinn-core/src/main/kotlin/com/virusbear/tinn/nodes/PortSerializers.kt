package com.virusbear.tinn.nodes

import com.virusbear.tinn.color.Color
import com.virusbear.tinn.math.*
import com.virusbear.tinn.registry.Registry

internal fun Registry<PortSerializer>.registerPortSerializers() {
    register("tinn:int", IntSerializer)
    register("tinn:double", DoubleSerializer)
    register("tinn:ivec2", IVec2Serializer)
    register("tinn:ivec3", IVec3Serializer)
    register("tinn:ivec4", IVec4Serializer)
    register("tinn:vec2", Vec2Serializer)
    register("tinn:vec3", Vec3Serializer)
    register("tinn:vec4", Vec4Serializer)
    register("tinn:color", ColorSerializer)
    register("tinn:mat3", Mat3Serializer)
    register("tinn:mat4", Mat4Serializer)
    register("tinn:string", StringSerializer)
}

val IntSerializer =
    PortSerializer(
        type = Int::class,
        serializer = { key, value, writer ->
            writer.write(key, value)
        },
        deserializer = { key, reader ->
            reader.int(key)
        }
    )

val DoubleSerializer =
    PortSerializer(
        type = Double::class,
        serializer = { key, value, writer ->
            writer.write(key, value)
        },
        deserializer = { key, reader ->
            reader.double(key)
        }
    )

val IVec2Serializer =
    PortSerializer(
        type = IVec2::class,
        serializer = { key, value, writer ->
            writer.write(key, value)
        },
        deserializer = { key, reader ->
            reader.iVec2(key)
        }
    )

val IVec3Serializer =
    PortSerializer(
        type = IVec3::class,
        serializer = { key, value, writer ->
            writer.write(key, value)
        },
        deserializer = { key, reader ->
            reader.iVec3(key)
        }
    )

val IVec4Serializer =
    PortSerializer(
        type = IVec4::class,
        serializer = { key, value, writer ->
            writer.write(key, value)
        },
        deserializer = { key, reader ->
            reader.iVec4(key)
        }
    )

val Vec2Serializer =
    PortSerializer(
        type = Vec2::class,
        serializer = { key, value, writer ->
            writer.write(key, value)
        },
        deserializer = { key, reader ->
            reader.vec2(key)
        }
    )

val Vec3Serializer =
    PortSerializer(
        type = Vec3::class,
        serializer = { key, value, writer ->
            writer.write(key, value)
        },
        deserializer = { key, reader ->
            reader.vec3(key)
        }
    )

val Vec4Serializer =
    PortSerializer(
        type = Vec4::class,
        serializer = { key, value, writer ->
            writer.write(key, value)
        },
        deserializer = { key, reader ->
            reader.vec4(key)
        }
    )

val ColorSerializer =
    PortSerializer(
        type = Color::class,
        serializer = { key, value, writer ->
            writer.write(key, value)
        },
        deserializer = { key, reader ->
            reader.color(key)
        }
    )

val Mat3Serializer =
    PortSerializer(
        type = Mat3::class,
        serializer = { key, value, writer ->
            writer.write(key, value)
        },
        deserializer = { key, reader ->
            reader.mat3(key)
        }
    )

val Mat4Serializer =
    PortSerializer(
        type = Mat4::class,
        serializer = { key, value, writer ->
            writer.write(key, value)
        },
        deserializer = { key, reader ->
            reader.mat4(key)
        }
    )

val StringSerializer =
    PortSerializer(
        type = String::class,
        serializer = { key, value, writer ->
            writer.write(key, value)
        },
        deserializer = { key, reader ->
            reader.string(key)
        }
    )