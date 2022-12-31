package com.virusbear.tinn

import com.virusbear.tinn.color.Color
import com.virusbear.tinn.math.*
import dev.dewy.nbt.Nbt
import dev.dewy.nbt.tags.collection.CompoundTag
import java.io.File

class NbtSceneWriter(private val root: CompoundTag = CompoundTag("root")): SceneWriter {

    override fun write(key: String, value: Byte) {
        root.putByte(key, value)
    }

    override fun write(key: String, value: Short) {
        root.putShort(key, value)
    }

    override fun write(key: String, value: Int) {
        root.putInt(key, value)
    }

    override fun write(key: String, value: Long) {
        root.putLong(key, value)
    }

    override fun write(key: String, value: Float) {
        root.putFloat(key, value)
    }

    override fun write(key: String, value: Double) {
        root.putDouble(key, value)
    }

    override fun write(key: String, value: IVec2) {
        writeCompound(key, value) {
            write("x", it.x)
            write("y", it.y)
        }
    }

    override fun write(key: String, value: IVec3) {
        writeCompound(key, value) {
            write("x", it.x)
            write("y", it.y)
            write("z", it.z)
        }
    }

    override fun write(key: String, value: IVec4) {
        writeCompound(key, value) {
            write("x", it.x)
            write("y", it.y)
            write("z", it.z)
            write("w", it.w)
        }
    }

    override fun write(key: String, value: Vec2) {
        writeCompound(key, value) {
            write("x", it.x)
            write("y", it.y)
        }
    }

    override fun write(key: String, value: Vec3) {
        writeCompound(key, value) {
            write("x", it.x)
            write("y", it.y)
            write("z", it.z)
        }
    }

    override fun write(key: String, value: Vec4) {
        writeCompound(key, value) {
            write("x", it.x)
            write("y", it.y)
            write("z", it.z)
            write("w", it.w)
        }
    }

    override fun write(key: String, value: Color) {
        writeCompound(key, value) {
            write("r", it.r)
            write("g", it.g)
            write("b", it.b)
            write("a", it.a)
        }
    }

    override fun write(key: String, value: Mat3) {
        writeCompound(key, value) {
            write("c1", Vec3(it.c0r0, it.c0r1, it.c0r2))
            write("c2", Vec3(it.c1r0, it.c1r1, it.c1r2))
            write("c3", Vec3(it.c2r0, it.c2r1, it.c2r2))
        }
    }

    override fun write(key: String, value: Mat4) {
        writeCompound(key, value) {
            write("c1", Vec4(it.c0r0, it.c0r1, it.c0r2, it.c0r3))
            write("c2", Vec4(it.c1r0, it.c1r1, it.c1r2, it.c1r3))
            write("c3", Vec4(it.c2r0, it.c2r1, it.c2r2, it.c2r3))
            write("c4", Vec4(it.c3r0, it.c3r1, it.c3r2, it.c3r3))
        }
    }

    override fun write(key: String, value: String) {
        root.putString(key, value)
    }

    override fun write(key: String, value: ByteArray) {
        root.putByteArray(key, value)
    }

    override fun write(key: String, value: IntArray) {
        root.putIntArray(key, value)
    }

    override fun write(key: String, value: LongArray) {
        root.putLongArray(key, value)
    }

    override fun <T> writeList(key: String, value: Collection<T>, block: SceneWriter.(T) -> Unit) {
        if(value.isNotEmpty())
            root.putList(key, value.map { NbtSceneWriter().apply { block(it) }.root })
    }

    override fun <T> writeCompound(key: String, value: T, block: SceneWriter.(T) -> Unit) {
        root.putCompound(key, NbtSceneWriter().apply { block(value) }.root.value)
    }

    override fun saveAs(file: File) {
        Nbt().toFile(root, file)
    }
}