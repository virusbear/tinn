package com.virusbear.tinn.serialization

interface Decoder {
    fun <T> decode(value: EncodedValue): T
}