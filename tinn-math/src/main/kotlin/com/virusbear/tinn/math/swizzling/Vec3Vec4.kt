package com.virusbear.tinn.math.swizzling

import com.virusbear.tinn.math.Vec3
import com.virusbear.tinn.math.Vec4

val Vec3.xyz0: Vec4
    get() = Vec4(x, y, z, 0.0)