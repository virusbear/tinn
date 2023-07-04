package com.virusbear.tinn.nodes

import com.virusbear.tinn.registry.Register
import com.virusbear.tinn.SceneReader
import com.virusbear.tinn.SceneWriter
import com.virusbear.tinn.color.Color
import com.virusbear.tinn.math.*
import com.virusbear.tinn.registry.Registry
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