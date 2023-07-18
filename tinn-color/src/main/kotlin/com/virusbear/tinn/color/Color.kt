package com.virusbear.tinn.color

data class Color(
    val r: Double = 0.0,
    val g: Double = 0.0,
    val b: Double = 0.0,
    val a: Double = 1.0
) {
    constructor(hex: Int): this(
        r = (hex shr 24) and 0xff,
        g = (hex shr 16) and 0xff,
        b = (hex shr 8) and 0xff,
        a = hex and 0xff
    )

    constructor(
        r: Int,
        g: Int,
        b: Int,
        a: Int = 0xff
    ): this(
        r.toDouble() / 0xff,
        g.toDouble() / 0xff,
        b.toDouble() / 0xff,
        a.toDouble() / 0xff
    )

    companion object {
        val TRANSPARENT = Color(0.0, 0.0, 0.0, 0.0)
        val BLACK = Color(0.0, 0.0, 0.0, 1.0)
        val WHITE = Color(1.0, 1.0, 1.0, 1.0)
    }
}

fun rgb(
    r: Int,
    g: Int,
    b: Int,
    a: Int = 0xff
): Color =
    Color(r, g, b, a)

fun hex(hex: Int): Color =
    if((hex shr 24) and 0xff == 0) {
        Color(hex shl 8 and 0xff)
    } else {
        Color(hex)
    }