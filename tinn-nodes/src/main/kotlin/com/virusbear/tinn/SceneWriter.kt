package com.virusbear.tinn

import com.virusbear.tinn.color.Color
import com.virusbear.tinn.math.*
import java.io.File

interface SceneWriter {
    fun write(key: String, value: Byte)
    fun write(key: String, value: Short)
    fun write(key: String, value: Int)
    fun write(key: String, value: Long)
    fun write(key: String, value: Float)
    fun write(key: String, value: Double)
    fun write(key: String, value: IVec2)
    fun write(key: String, value: IVec3)
    fun write(key: String, value: IVec4)
    fun write(key: String, value: Vec2)
    fun write(key: String, value: Vec3)
    fun write(key: String, value: Vec4)
    fun write(key: String, value: Color)
    fun write(key: String, value: Mat3)
    fun write(key: String, value: Mat4)

    fun write(key: String, value: String)
    //TODO: redesign array, list and compound serialization
    fun write(key: String, value: ByteArray)
    fun write(key: String, value: IntArray)
    fun write(key: String, value: LongArray)
    fun <T> writeList(key: String, value: Collection<T>, block: SceneWriter.(T) -> Unit)
    fun <T> writeCompound(key: String, value: T, block: SceneWriter.(T) -> Unit)
}