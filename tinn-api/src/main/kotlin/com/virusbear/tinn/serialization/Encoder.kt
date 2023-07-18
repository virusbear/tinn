package com.virusbear.tinn.serialization

interface Encoder {
    fun <T> encode(value: T): EncodedValue
}