package com.virusbear.tinn

class MipMapLevel private constructor(val levels: Int) {
    companion object {
        val None = MipMapLevel(0)
        fun Count(levels: UInt) = MipMapLevel(levels.toInt())
    }
}