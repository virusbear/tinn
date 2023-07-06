package com.virusbear.tinn.math.swizzling

import com.virusbear.tinn.math.IVec2

val IVec2.xx: IVec2
    get() = IVec2(x, x)
val IVec2.xy: IVec2
    get() = IVec2(x, y)
val IVec2.yx: IVec2
    get() = IVec2(y, x)
val IVec2.yy: IVec2
    get() = IVec2(y, y)