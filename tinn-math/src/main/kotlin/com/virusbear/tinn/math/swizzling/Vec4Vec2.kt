package com.virusbear.tinn.math.swizzling

import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.math.Vec4

val Vec4.xx: Vec2
    get() = Vec2(x, x)
val Vec4.xy: Vec2
    get() = Vec2(x, y)
val Vec4.xz: Vec2
    get() = Vec2(x, z)
val Vec4.xw: Vec2
    get() = Vec2(x, w)
val Vec4.yx: Vec2
    get() = Vec2(y, x)
val Vec4.yy: Vec2
    get() = Vec2(y, y)
val Vec4.yz: Vec2
    get() = Vec2(y, z)
val Vec4.yw: Vec2
    get() = Vec2(y, w)
val Vec4.zx: Vec2
    get() = Vec2(z, x)
val Vec4.zy: Vec2
    get() = Vec2(z, y)
val Vec4.zz: Vec2
    get() = Vec2(z, z)
val Vec4.zw: Vec2
    get() = Vec2(z, w)
val Vec4.wx: Vec2
    get() = Vec2(w, x)
val Vec4.wy: Vec2
    get() = Vec2(w, y)
val Vec4.wz: Vec2
    get() = Vec2(w, z)
val Vec4.ww: Vec2
    get() = Vec2(w, w)