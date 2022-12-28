package com.virusbear.tinn

import com.virusbear.tinn.color.Color
import com.virusbear.tinn.math.IVec2

interface ColorBufferReader: BufferReader {
    operator fun get(x: Int, y: Int): Color
    operator fun get(texCoord: IVec2): Color =
        get(texCoord.x, texCoord.y)
}