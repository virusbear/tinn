package com.virusbear.tinn.math.swizzling

import com.virusbear.tinn.math.IVec3
import com.virusbear.tinn.math.IVec4

val IVec3.xyz0: IVec4
    get() = IVec4(x, y, z, 0)