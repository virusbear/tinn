package com.virusbear.tinn.math

val IVec2.vec: Vec2
    get() = Vec2(x.toDouble(), y.toDouble())

val Vec2.ivec: IVec2
    get() = IVec2(x.toInt(), y.toInt())

val IVec3.vec: Vec3
    get() = Vec3(x.toDouble(), y.toDouble(), z.toDouble())

val Vec3.ivec: IVec3
    get() = IVec3(x.toInt(), y.toInt(), z.toInt())

val IVec4.vec: Vec4
    get() = Vec4(x.toDouble(), y.toDouble(), z.toDouble(), w.toDouble())

val Vec4.ivec: IVec4
    get() = IVec4(x.toInt(), y.toInt(), z.toInt(), w.toInt())