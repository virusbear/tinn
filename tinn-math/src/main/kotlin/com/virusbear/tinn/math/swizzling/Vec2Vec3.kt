package com.virusbear.tinn.math.swizzling

import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.math.Vec3

val Vec2.xxx: Vec3
    get() = Vec3(x, x, x)
val Vec2.xxy: Vec3
    get() = Vec3(x, x, y)
val Vec2.xyx: Vec3
    get() = Vec3(x, y, x)
val Vec2.xyy: Vec3
    get() = Vec3(x, y, y)
val Vec2.yxx: Vec3
    get() = Vec3(y, x, x)
val Vec2.yxy: Vec3
    get() = Vec3(y, x, y)
val Vec2.yyx: Vec3
    get() = Vec3(y, y, x)
val Vec2.yyy: Vec3
    get() = Vec3(y, y, y)

val Vec2.xy0: Vec3
    get() = Vec3(x, y, 0.0)