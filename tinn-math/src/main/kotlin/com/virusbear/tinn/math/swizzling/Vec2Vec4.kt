package com.virusbear.tinn.math.swizzling

import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.math.Vec4

val Vec2.xxxx: Vec4
    get() = Vec4(x, x, x, x)
val Vec2.xxxy: Vec4
    get() = Vec4(x, x, x, y)
val Vec2.xxyx: Vec4
    get() = Vec4(x, x, y, x)
val Vec2.xxyy: Vec4
    get() = Vec4(x, x, y, y)
val Vec2.xyxx: Vec4
    get() = Vec4(x, y, x, x)
val Vec2.xyxy: Vec4
    get() = Vec4(x, y, x, y)
val Vec2.xyyx: Vec4
    get() = Vec4(x, y, y, x)
val Vec2.xyyy: Vec4
    get() = Vec4(x, y, y, y)
val Vec2.yxxx: Vec4
    get() = Vec4(y, x, x, x)
val Vec2.yxxy: Vec4
    get() = Vec4(y, x, x, y)
val Vec2.yxyx: Vec4
    get() = Vec4(y, x, y, x)
val Vec2.yxyy: Vec4
    get() = Vec4(y, x, y, y)
val Vec2.yyxx: Vec4
    get() = Vec4(y, y, x, x)
val Vec2.yyxy: Vec4
    get() = Vec4(y, y, x, y)
val Vec2.yyyx: Vec4
    get() = Vec4(y, y, y, x)
val Vec2.yyyy: Vec4
    get() = Vec4(y, y, y, y)