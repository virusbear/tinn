package com.virusbear.tinn.serialization

interface Format {
    fun load(data: ByteArray): EncodedValue
    fun save(value: EncodedValue): ByteArray
}