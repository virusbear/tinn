package com.virusbear.tinn.serialization

import com.virusbear.tinn.TinnPlugin
import com.virusbear.tinn.color.Color
import com.virusbear.tinn.color.rgb
import dev.dewy.nbt.Nbt
import dev.dewy.nbt.api.Tag
import dev.dewy.nbt.tags.TagType
import dev.dewy.nbt.tags.array.ByteArrayTag
import dev.dewy.nbt.tags.array.IntArrayTag
import dev.dewy.nbt.tags.array.LongArrayTag
import dev.dewy.nbt.tags.collection.CompoundTag
import dev.dewy.nbt.tags.collection.ListTag
import dev.dewy.nbt.tags.primitive.ByteTag
import dev.dewy.nbt.tags.primitive.DoubleTag
import dev.dewy.nbt.tags.primitive.FloatTag
import dev.dewy.nbt.tags.primitive.IntTag
import dev.dewy.nbt.tags.primitive.LongTag
import dev.dewy.nbt.tags.primitive.ShortTag
import dev.dewy.nbt.tags.primitive.StringTag
import java.io.File

object NbtFormat: Format {
    override fun load(data: ByteArray): EncodedValue =
        Nbt().fromByteArray(data).toEncodedValue()

    override fun save(value: EncodedValue): ByteArray =
        value.toTag("data")?.let {
            Nbt().toSnbt(CompoundTag("root").apply { put<Tag>(it) }).toByteArray()
        } ?: byteArrayOf()

    private fun Tag.toEncodedValue(): EncodedValue =
        when(typeId) {
            TagType.BYTE.id -> EncodedValue.ByteValue((this as ByteTag).byteValue())
            TagType.SHORT.id -> EncodedValue.IntValue((this as ShortTag).intValue())
            TagType.INT.id -> EncodedValue.IntValue((this as IntTag).intValue())
            TagType.LONG.id -> EncodedValue.LongValue((this as LongTag).longValue())
            TagType.FLOAT.id -> EncodedValue.DoubleValue((this as FloatTag).doubleValue())
            TagType.DOUBLE.id -> EncodedValue.DoubleValue((this as DoubleTag).doubleValue())
            TagType.BYTE_ARRAY.id -> (this as ByteArrayTag).value?.let { EncodedValue.ByteArrayValue(it) }
            TagType.STRING.id -> (this as StringTag).value?.let { EncodedValue.StringValue(it) }
            TagType.LIST.id -> EncodedValue.ListValue((this as ListTag<*>).map { toEncodedValue() })
            TagType.COMPOUND.id -> EncodedValue.CompoundValue((this as CompoundTag).associate { it.name to it.toEncodedValue() })
            TagType.INT_ARRAY.id -> (this as IntArrayTag).value?.let { EncodedValue.IntArrayValue(it) }
            TagType.LONG_ARRAY.id -> (this as LongArrayTag).value?.let { EncodedValue.LongArrayValue(it) }
            else -> error("Unknown NBT tag type id: $typeId")
        } ?: EncodedValue.NullValue

    private fun EncodedValue.toTag(name: String): Tag? =
        when(this) {
            is EncodedValue.BooleanValue -> ByteTag(name, if(value) 0xff else 0x00)
            is EncodedValue.ByteArrayValue -> ByteArrayTag(name, value)
            is EncodedValue.ByteValue -> ByteTag(name, value)
            is EncodedValue.CompoundValue -> CompoundTag(name, value.mapValues { (k, v) -> v.toTag(k) })
            is EncodedValue.DoubleArrayValue -> ListTag(name, value.map { DoubleTag(it) })
            is EncodedValue.DoubleValue -> DoubleTag(name, value)
            is EncodedValue.IntArrayValue -> IntArrayTag(name, value)
            is EncodedValue.IntValue -> IntTag(name, value)
            is EncodedValue.ListValue<*> -> ListTag(name, value.map { toTag("") })
            is EncodedValue.LongArrayValue -> LongArrayTag(name, value)
            is EncodedValue.LongValue -> LongTag(name, value)
            is EncodedValue.StringValue -> StringTag(name, value)
            EncodedValue.NullValue -> null
        }
}