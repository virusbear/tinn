package com.virusbear.tinn

import com.virusbear.tinn.math.IVec2

interface Monitor {
    val size: IVec2
    val position: IVec2
    val contentScale: Double
    val dpi: Double
    val pixelAspectRatio: Double
    val refreshRate: Int
    val name: String
    val isPrimary: Boolean
}