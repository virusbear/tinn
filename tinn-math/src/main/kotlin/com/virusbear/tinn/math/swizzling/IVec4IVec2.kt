package com.virusbear.tinn.math.swizzling

import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.IVec4

val IVec4.xx: IVec2
    get() = IVec2(x, x)
val IVec4.xy: IVec2
    get() = IVec2(x, y)
val IVec4.xz: IVec2
    get() = IVec2(x, z)
val IVec4.xw: IVec2
    get() = IVec2(x, w)
val IVec4.yx: IVec2
    get() = IVec2(y, x)
val IVec4.yy: IVec2
    get() = IVec2(y, y)
val IVec4.yz: IVec2
    get() = IVec2(y, z)
val IVec4.yw: IVec2
    get() = IVec2(y, w)
val IVec4.zx: IVec2
    get() = IVec2(z, x)
val IVec4.zy: IVec2
    get() = IVec2(z, y)
val IVec4.zz: IVec2
    get() = IVec2(z, z)
val IVec4.zw: IVec2
    get() = IVec2(z, w)
val IVec4.wx: IVec2
    get() = IVec2(w, x)
val IVec4.wy: IVec2
    get() = IVec2(w, y)
val IVec4.wz: IVec2
    get() = IVec2(w, z)
val IVec4.ww: IVec2
    get() = IVec2(w, w)