package com.virusbear.tinn.serialization.nbt

import com.virusbear.tinn.serialization.BinaryFormat
import com.virusbear.tinn.serialization.CompoundEncoder
import com.virusbear.tinn.serialization.Encoder
import com.virusbear.tinn.serialization.TextFormat
import dev.dewy.nbt.api.Tag
import dev.dewy.nbt.tags.collection.CompoundTag
import kotlin.reflect.KClass

class Nbt: BinaryFormat, TextFormat {
    override fun <T> encodeToByteArray(value: T): ByteArray {
        TODO("Not yet implemented")
    }

    override fun <T : Any> decodeFromByteArray(kClass: KClass<T>, value: ByteArray): T {
        TODO("Not yet implemented")
    }

    override fun <T> encodeToString(value: T): String {
        TODO("Not yet implemented")
    }

    override fun <T : Any> decodeFromString(kClass: KClass<T>, value: String): T {
        TODO("Not yet implemented")
    }
}

class NbtEncoder: Encoder {
    override fun <T> encode(value: T) {
        TODO("Not yet implemented")
    }

    override fun boolean(value: Boolean) {
        TODO("Not yet implemented")
    }

    override fun byte(value: Byte) {
        TODO("Not yet implemented")
    }

    override fun short(value: Short) {
        TODO("Not yet implemented")
    }

    override fun int(value: Int) {
        TODO("Not yet implemented")
    }

    override fun long(value: Long) {
        TODO("Not yet implemented")
    }

    override fun float(value: Float) {
        TODO("Not yet implemented")
    }

    override fun double(value: Double) {
        TODO("Not yet implemented")
    }

    override fun string(value: String) {
        TODO("Not yet implemented")
    }

    override fun byteArray(value: ByteArray) {
        TODO("Not yet implemented")
    }

    override fun <T> list(value: List<T>, block: Encoder.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun compound(block: CompoundEncoder.() -> Unit) {
        TODO("Not yet implemented")
    }
}

class NbtCompoundEncoder: CompoundEncoder {
    private val root = CompoundTag()

    override fun <T : Any> encode(key: String, kClass: KClass<T>, value: T) {
        TODO("Not yet implemented")
    }

    override fun boolean(key: String, value: Boolean) {
        byte(key, (if(value) 0xff else 0x00).toByte())
    }

    override fun byte(key: String, value: Byte) {
        root.putByte(key, value)
    }

    override fun short(key: String, value: Short) {
        int(key, value.toInt())
    }

    override fun int(key: String, value: Int) {
        root.putInt(key, value)
    }

    override fun long(key: String, value: Long) {
        root.putLong(key, value)
    }

    override fun float(key: String, value: Float) {
        root.putFloat(key, value)
    }

    override fun double(key: String, value: Double) {
        root.putDouble(key, value)
    }

    override fun string(key: String, value: String) {
        root.putString(key, value)
    }

    override fun byteArray(key: String, value: ByteArray) {
        root.putByteArray(key, value)
    }

    override fun <T> list(key: String, value: List<T>, block: Encoder.(T) -> Unit) {
        root.putList(key, value.map {
            NbtEncoder().apply { block(it) }.toTag()
        })
    }

    override fun compound(key: String, block: CompoundEncoder.() -> Unit) {
        root.putCompound(key, NbtCompoundEncoder().apply(block).toTag())
    }

    internal fun toTag(): Map<String, Tag> =
        root.value
}