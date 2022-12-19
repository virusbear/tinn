package com.virusbear.tinn

class MultiSample private constructor(val samples: Int) {
    companion object {
        val None = MultiSample(0)
        fun Count(samples: UInt) = MultiSample(samples.toInt())
    }
}