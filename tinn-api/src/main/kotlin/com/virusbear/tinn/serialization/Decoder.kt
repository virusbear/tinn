package com.virusbear.tinn.serialization

import kotlin.reflect.KClass

interface Decoder {
    fun <T: Any> decode(kClass: KClass<T>, value: EncodedValue): T
}