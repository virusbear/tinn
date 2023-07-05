package com.virusbear.tinn.math.swizzling

import com.virusbear.tinn.math.Vec2

val Vec2.xx: Vec2
    get() = Vec2(x, x)
val Vec2.yy: Vec2
    get() = Vec2(y, y)
val Vec2.xy: Vec2
    get() = Vec2(x, y)
val Vec2.yx: Vec2
    get() = Vec2(y, x)