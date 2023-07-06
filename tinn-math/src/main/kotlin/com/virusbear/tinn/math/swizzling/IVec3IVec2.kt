package com.virusbear.tinn.math.swizzling

import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.IVec3

val IVec3.xx: IVec2
    get() = IVec2(x, x)
val IVec3.xy: IVec2
    get() = IVec2(x, y)
val IVec3.xz: IVec2
    get() = IVec2(x, z)
val IVec3.yx: IVec2
    get() = IVec2(y, x)
val IVec3.yy: IVec2
    get() = IVec2(y, y)
val IVec3.yz: IVec2
    get() = IVec2(y, z)
val IVec3.zx: IVec2
    get() = IVec2(z, x)
val IVec3.zy: IVec2
    get() = IVec2(x, y)
val IVec3.zz: IVec2
    get() = IVec2(z, z)