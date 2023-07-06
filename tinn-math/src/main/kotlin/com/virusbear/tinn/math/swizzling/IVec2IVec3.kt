package com.virusbear.tinn.math.swizzling

import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.IVec3
import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.math.Vec3

val IVec2.xxx: IVec3
    get() = IVec3(x, x, x)
val IVec2.xxy: IVec3
    get() = IVec3(x, x, y)
val IVec2.xyx: IVec3
    get() = IVec3(x, y, x)
val IVec2.xyy: IVec3
    get() = IVec3(x, y, y)
val IVec2.yxx: IVec3
    get() = IVec3(y, x, x)
val IVec2.yxy: IVec3
    get() = IVec3(y, x, y)
val IVec2.yyx: IVec3
    get() = IVec3(y, y, x)
val IVec2.yyy: IVec3
    get() = IVec3(y, y, y)

val IVec2.xy0: IVec3
    get() = IVec3(x, y, 0)