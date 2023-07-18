package com.virusbear.tinn.serialization

object BooleanSerializer: Serializer<Boolean> {
    override fun serialize(value: Boolean, encoder: Encoder): EncodedValue =
        EncodedValue.BooleanValue(value)

    override fun deserialize(value: EncodedValue, decoder: Decoder): Boolean {
        require(value is EncodedValue.BooleanValue)

        return value.value
    }
}

object ByteSerializer: Serializer<Byte> {
    override fun serialize(value: Byte, encoder: Encoder): EncodedValue =
        EncodedValue.ByteValue(value)

    override fun deserialize(value: EncodedValue, decoder: Decoder): Byte {
        require(value is EncodedValue.ByteValue)

        return value.value
    }
}

object IntSerializer: Serializer<Int> {
    override fun serialize(value: Int, encoder: Encoder): EncodedValue =
        EncodedValue.IntValue(value)

    override fun deserialize(value: EncodedValue, decoder: Decoder): Int {
        require(value is EncodedValue.IntValue)

        return value.value
    }
}

object LongSerializer: Serializer<Long> {
    override fun serialize(value: Long, encoder: Encoder): EncodedValue =
        EncodedValue.LongValue(value)

    override fun deserialize(value: EncodedValue, decoder: Decoder): Long {
        require(value is EncodedValue.LongValue)

        return value.value
    }
}

object DoubleSerializer: Serializer<Double> {
    override fun serialize(value: Double, encoder: Encoder): EncodedValue =
        EncodedValue.DoubleValue(value)

    override fun deserialize(value: EncodedValue, decoder: Decoder): Double {
        require(value is EncodedValue.DoubleValue)

        return value.value
    }
}

object StringSerializer: Serializer<String> {
    override fun serialize(value: String, encoder: Encoder): EncodedValue =
        EncodedValue.StringValue(value)

    override fun deserialize(value: EncodedValue, decoder: Decoder): String {
        require(value is EncodedValue.StringValue)

        return value.value
    }
}

object ByteArraySerializer: Serializer<ByteArray> {
    override fun serialize(value: ByteArray, encoder: Encoder): EncodedValue =
        EncodedValue.ByteArrayValue(value)

    override fun deserialize(value: EncodedValue, decoder: Decoder): ByteArray {
        require(value is EncodedValue.ByteArrayValue)

        return value.value
    }
}

object IntArraySerializer: Serializer<IntArray> {
    override fun serialize(value: IntArray, encoder: Encoder): EncodedValue =
        EncodedValue.IntArrayValue(value)

    override fun deserialize(value: EncodedValue, decoder: Decoder): IntArray {
        require(value is EncodedValue.IntArrayValue)

        return value.value
    }
}

object LongArraySerializer: Serializer<LongArray> {
    override fun serialize(value: LongArray, encoder: Encoder): EncodedValue =
        EncodedValue.LongArrayValue(value)

    override fun deserialize(value: EncodedValue, decoder: Decoder): LongArray {
        require(value is EncodedValue.LongArrayValue)

        return value.value
    }
}

object DoubleArraySerializer: Serializer<DoubleArray> {
    override fun serialize(value: DoubleArray, encoder: Encoder): EncodedValue =
        EncodedValue.DoubleArrayValue(value)

    override fun deserialize(value: EncodedValue, decoder: Decoder): DoubleArray {
        require(value is EncodedValue.DoubleArrayValue)

        return value.value
    }
}