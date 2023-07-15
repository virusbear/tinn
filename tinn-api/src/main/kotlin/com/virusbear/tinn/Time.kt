package com.virusbear.tinn

@JvmInline
value class Time(val s: Double) {
    val ns: Double
        get() = us * 1e3
    val us: Double
        get() = ms * 1e3
    val ms: Double
        get() = s * 1e3
    val m: Double
        get() = s / 60.0
    val h: Double
        get() = m / 60.0
    val days: Double
        get() = h / 24.0
}