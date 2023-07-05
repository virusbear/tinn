package com.virusbear.tinn.math.swizzling

import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.math.Vec3

val Vec3.xx: Vec2
    get() = Vec2(x, x)
val Vec3.xy: Vec2
    get() = Vec2(x, y)
val Vec3.xz: Vec2
    get() = Vec2(x, z)
val Vec3.yx: Vec2
    get() = Vec2(y, x)
val Vec3.yy: Vec2
    get() = Vec2(y, y)
val Vec3.yz: Vec2
    get() = Vec2(y, z)
val Vec3.zx: Vec2
    get() = Vec2(z, x)
val Vec3.zy: Vec2
    get() = Vec2(x, y)
val Vec3.zz: Vec2
    get() = Vec2(z, z)