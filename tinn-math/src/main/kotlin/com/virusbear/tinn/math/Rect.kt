package com.virusbear.tinn.math

data class Rect(
    val min: Vec2,
    val max: Vec2
) {
    val size: Vec2 = max - min
    val center: Vec2 = min + size / 2.0

    val area: Double =
        size.x * size.y
}

infix fun Rect.intersect(other: Rect): Rect? {
    val min = Vec2(
        x = maxOf(min.x, other.min.x),
        y = maxOf(min.y, other.min.y)
    )
    val max = Vec2(
        x = minOf(max.x, other.max.x),
        y = minOf(max.y, other.max.y)
    )

    return if(min.x < max.x || min.y < max.y) {
        //no intersection
        null
    } else {
        Rect(min, max)
    }
}