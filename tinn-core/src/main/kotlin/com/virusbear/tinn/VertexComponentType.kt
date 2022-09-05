package com.virusbear.tinn

enum class VertexComponentType(val bytes: Int) {
    HALF_FLOAT(2),
    FLOAT(4),
    DOUBLE(8),
    FIXED(2),
    BYTE(1),
    UBYTE(1),
    SHORT(2),
    USHORT(2),
    INT(4),
    UINT(4)
}