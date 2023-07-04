package com.virusbear.tinn

import com.virusbear.tinn.color.Color
import com.virusbear.tinn.math.*
import dev.dewy.nbt.Nbt
import dev.dewy.nbt.tags.collection.CompoundTag
import java.io.File

class NbtSceneReader(private val root: CompoundTag): SceneReader {
    //TODO: move this to NbtSceneFile
    //TODO: create new interface SceneFile
    constructor(file: File): this(Nbt().fromFile(file))
    constructor(bytes: ByteArray): this(Nbt().fromByteArray(bytes))

    override fun byte(key: String): Byte =
        root.getByte(key).byteValue()

    override fun short(key: String): Short =
        root.getShort(key).shortValue()

    override fun int(key: String): Int =
        root.getInt(key).intValue()

    override fun long(key: String): Long =
        root.getLong(key).longValue()

    override fun float(key: String): Float =
        root.getFloat(key).floatValue()

    override fun double(key: String): Double =
        root.getDouble(key).doubleValue()

    override fun iVec2(key: String): IVec2 =
        compound(key) {
            IVec2(
                int("x"),
                int("y")
            )
        }

    override fun iVec3(key: String): IVec3 =
        compound(key) {
            IVec3(
                int("x"),
                int("y"),
                int("z")
            )
        }

    override fun iVec4(key: String): IVec4 =
        compound(key) {
            IVec4(
                int("x"),
                int("y"),
                int("z"),
                int("w")
            )
        }

    override fun vec2(key: String): Vec2 =
        compound(key) {
            Vec2(
                double("x"),
                double("y")
            )
        }

    override fun vec3(key: String): Vec3 =
        compound(key) {
            Vec3(
                double("x"),
                double("y"),
                double("z")
            )
        }

    override fun vec4(key: String): Vec4 =
        compound(key) {
            Vec4(
                double("x"),
                double("y"),
                double("z"),
                double("w")
            )
        }

    override fun color(key: String): Color =
        compound(key) {
            Color(
                double("r"),
                double("g"),
                double("b"),
                double("a")
            )
        }

    override fun mat3(key: String): Mat3 =
        compound(key) {
            val c1 = vec3("c1")
            val c2 = vec3("c2")
            val c3 = vec3("c3")

            Mat3(
                c1.x, c2.x, c3.x,
                c1.y, c2.y, c3.y,
                c1.z, c2.z, c3.z
            )
        }

    override fun mat4(key: String): Mat4 =
        compound(key) {
            val c1 = vec4("c1")
            val c2 = vec4("c2")
            val c3 = vec4("c3")
            val c4 = vec4("c4")

            Mat4(
                c1.x, c2.x, c3.x, c4.x,
                c1.y, c2.y, c3.y, c4.y,
                c1.z, c2.z, c3.z, c4.z,
                c1.w, c2.w, c3.w, c4.w
            )
        }

    override fun string(key: String): String =
        root.getString(key).value

    override fun byteArray(key: String): ByteArray =
        root.getByteArray(key).value

    override fun intArray(key: String): IntArray =
        root.getIntArray(key).value

    override fun longArray(key: String): LongArray =
        root.getLongArray(key).value

    //TODO: how to read non compound lists
    override fun <T> list(key: String, block: SceneReader.() -> T): List<T> =
        if(key !in this) {
            emptyList()
        } else {
            root.getList<CompoundTag>(key).map { NbtSceneReader(it).block() }
        }


    override fun <T> compound(key: String, block: SceneReader.() -> T): T =
        NbtSceneReader(root.getCompound(key)).block()

    override fun compound(key: String): SceneReader =
        NbtSceneReader(root.getCompound(key))

    override fun contains(key: String): Boolean =
        root.contains(key)
}