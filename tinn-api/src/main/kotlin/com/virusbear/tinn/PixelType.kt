package com.virusbear.tinn

enum class PixelType(val bytes: Int) {
    I8(1),
    I16(2),
    I32(4),
    F16(2),
    F32(4)
}