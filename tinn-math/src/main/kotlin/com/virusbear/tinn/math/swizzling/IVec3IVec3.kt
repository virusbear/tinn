package com.virusbear.tinn.math.swizzling

import com.virusbear.tinn.math.IVec3

val IVec3.xxx: IVec3
    get() = IVec3(x, x, x)
val IVec3.xxy: IVec3
    get() = IVec3(x, x, y)
val IVec3.xxz: IVec3
    get() = IVec3(x, x, z)
val IVec3.xyx: IVec3
    get() = IVec3(x, y, x)
val IVec3.xyy: IVec3
    get() = IVec3(x, y, y)
val IVec3.xyz: IVec3
    get() = IVec3(x, y, z)
val IVec3.xzx: IVec3
    get() = IVec3(x, z, x)
val IVec3.xzy: IVec3
    get() = IVec3(x, z, y)
val IVec3.xzz: IVec3
    get() = IVec3(x, z, z)
val IVec3.yxx: IVec3
    get() = IVec3(y, x, x)
val IVec3.yxy: IVec3
    get() = IVec3(y, x, y)
val IVec3.yxz: IVec3
    get() = IVec3(y, x, z)
val IVec3.yyx: IVec3
    get() = IVec3(y, y, x)
val IVec3.yyy: IVec3
    get() = IVec3(y, y, y)
val IVec3.yyz: IVec3
    get() = IVec3(y, y, z)
val IVec3.yzx: IVec3
    get() = IVec3(y, z, x)
val IVec3.yzy: IVec3
    get() = IVec3(y, z, y)
val IVec3.yzz: IVec3
    get() = IVec3(y, z, z)
val IVec3.zxx: IVec3
    get() = IVec3(z, x, x)
val IVec3.zxy: IVec3
    get() = IVec3(z, x, y)
val IVec3.zxz: IVec3
    get() = IVec3(z, x, z)
val IVec3.zyx: IVec3
    get() = IVec3(z, y, x)
val IVec3.zyy: IVec3
    get() = IVec3(z, y, y)
val IVec3.zyz: IVec3
    get() = IVec3(z, y, z)
val IVec3.zzx: IVec3
    get() = IVec3(z, z, x)
val IVec3.zzy: IVec3
    get() = IVec3(z, z, y)
val IVec3.zzz: IVec3
    get() = IVec3(z, z, z)