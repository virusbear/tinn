package com.virusbear.tinn.math

import kotlin.math.PI

val Double.rad: Radians
    get() = Radians(this)

val Double.deg: Degrees
    get() = Degrees(this)

@JvmInline
value class Radians(val value: Double)

@JvmInline
value class Degrees(val value: Double)

val Degrees.rad: Radians
    get() = (value * (PI / 180.0)).rad

val Radians.deg: Degrees
    get() = (value * (180.0 / PI)).deg