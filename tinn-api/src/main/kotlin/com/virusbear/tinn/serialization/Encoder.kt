package com.virusbear.tinn.serialization

import kotlin.reflect.KClass

interface Encoder {
    fun <T: Any> encode(kClass: KClass<T>, value: T): EncodedValue
}