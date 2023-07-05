package com.virusbear.tinn.math.swizzling

import com.virusbear.tinn.math.Vec3

val Vec3.xxx: Vec3
    get() = Vec3(x, x, x)
val Vec3.xxy: Vec3
    get() = Vec3(x, x, y)
val Vec3.xxz: Vec3
    get() = Vec3(x, x, z)
val Vec3.xyx: Vec3
    get() = Vec3(x, y, x)
val Vec3.xyy: Vec3
    get() = Vec3(x, y, y)
val Vec3.xyz: Vec3
    get() = Vec3(x, y, z)
val Vec3.xzx: Vec3
    get() = Vec3(x, z, x)
val Vec3.xzy: Vec3
    get() = Vec3(x, z, y)
val Vec3.xzz: Vec3
    get() = Vec3(x, z, z)
val Vec3.yxx: Vec3
    get() = Vec3(y, x, x)
val Vec3.yxy: Vec3
    get() = Vec3(y, x, y)
val Vec3.yxz: Vec3
    get() = Vec3(y, x, z)
val Vec3.yyx: Vec3
    get() = Vec3(y, y, x)
val Vec3.yyy: Vec3
    get() = Vec3(y, y, y)
val Vec3.yyz: Vec3
    get() = Vec3(y, y, z)
val Vec3.yzx: Vec3
    get() = Vec3(y, z, x)
val Vec3.yzy: Vec3
    get() = Vec3(y, z, y)
val Vec3.yzz: Vec3
    get() = Vec3(y, z, z)
val Vec3.zxx: Vec3
    get() = Vec3(z, x, x)
val Vec3.zxy: Vec3
    get() = Vec3(z, x, y)
val Vec3.zxz: Vec3
    get() = Vec3(z, x, z)
val Vec3.zyx: Vec3
    get() = Vec3(z, y, x)
val Vec3.zyy: Vec3
    get() = Vec3(z, y, y)
val Vec3.zyz: Vec3
    get() = Vec3(z, y, z)
val Vec3.zzx: Vec3
    get() = Vec3(z, z, x)
val Vec3.zzy: Vec3
    get() = Vec3(z, z, y)
val Vec3.zzz: Vec3
    get() = Vec3(z, z, z)