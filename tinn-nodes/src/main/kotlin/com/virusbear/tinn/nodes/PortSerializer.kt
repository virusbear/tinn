package com.virusbear.tinn.nodes

import com.virusbear.tinn.registry.Register
import com.virusbear.tinn.SceneReader
import com.virusbear.tinn.SceneWriter
import com.virusbear.tinn.color.Color
import com.virusbear.tinn.math.*
import kotlin.reflect.KClass

interface PortSerializer {
    val type: KClass<*>
    fun serialize(key: String, value: Any, writer: SceneWriter)
    fun deserialize(key: String, reader: SceneReader): Any?
}

inline fun <reified T: Any> PortSerializer(type: KClass<T>, noinline serializer: (String, T, SceneWriter) -> Unit, noinline deserializer: (String, SceneReader) -> T?) =
    object: PortSerializer {
        override val type: KClass<*> = type

        override fun serialize(key: String, value: Any, writer: SceneWriter) {
            if(value is T) {
                serializer(key, value, writer)
            }
        }

        override fun deserialize(key: String, reader: SceneReader): Any? =
            deserializer(key, reader)
    }

@Register("tinn:int")
val IntPortSerializer =
    PortSerializer(
        type = Int::class,
        serializer = { key, value, writer ->
            writer.write(key, value as Int)
        },
        deserializer = { key, reader ->
            reader.int(key)
        }
    )

@Register("tinn:double")
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

@Register("tinn:ivec2")
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

@Register("tinn:ivec3")
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

@Register("tinn:ivec4")
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

@Register("tinn:vec2")
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

@Register("tinn:vec3")
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

@Register("tinn:vec4")
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

@Register("tinn:color")
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

@Register("tinn:mat3")
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

@Register("tinn:mat4")
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

@Register("tinn:string")
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