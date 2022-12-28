package com.virusbear.tinn

import com.virusbear.tinn.color.Color
import com.virusbear.tinn.math.IVec2

interface ColorBufferWriter: BufferWriter {
    operator fun set(x: Int, y: Int, color: Color)
    operator fun set(texCoord: IVec2, color: Color) =
        set(texCoord.x, texCoord.y, color)
}