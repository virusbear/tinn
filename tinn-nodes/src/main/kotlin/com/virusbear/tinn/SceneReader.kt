package com.virusbear.tinn

import com.virusbear.tinn.color.Color
import com.virusbear.tinn.math.*
import com.virusbear.tinn.math.Vec4 as Vec4

interface SceneReader {
    fun byte(key: String): Byte
    fun short(key: String): Short
    fun int(key: String): Int
    fun long(key: String): Long
    fun float(key: String): Float
    fun double(key: String): Double
    fun iVec2(key: String): IVec2
    fun iVec3(key: String): IVec3
    fun iVec4(key: String): IVec4
    fun vec2(key: String): Vec2
    fun vec3(key: String): Vec3
    fun vec4(key: String): Vec4
    fun color(key: String): Color
    fun mat3(key: String): Mat3
    fun mat4(key: String): Mat4
    fun string(key: String): String
    fun byteArray(key: String): ByteArray
    fun intArray(key: String): IntArray
    fun longArray(key: String): LongArray
    fun <T> list(key: String, block: SceneReader.() -> T): List<T>
    fun <T> compound(key: String, block: SceneReader.() -> T): T
}