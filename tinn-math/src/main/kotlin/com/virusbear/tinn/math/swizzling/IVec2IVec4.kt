package com.virusbear.tinn.math.swizzling

import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.IVec4

val IVec2.xxxx: IVec4
    get() = IVec4(x, x, x, x)
val IVec2.xxxy: IVec4
    get() = IVec4(x, x, x, y)
val IVec2.xxyx: IVec4
    get() = IVec4(x, x, y, x)
val IVec2.xxyy: IVec4
    get() = IVec4(x, x, y, y)
val IVec2.xyxx: IVec4
    get() = IVec4(x, y, x, x)
val IVec2.xyxy: IVec4
    get() = IVec4(x, y, x, y)
val IVec2.xyyx: IVec4
    get() = IVec4(x, y, y, x)
val IVec2.xyyy: IVec4
    get() = IVec4(x, y, y, y)
val IVec2.yxxx: IVec4
    get() = IVec4(y, x, x, x)
val IVec2.yxxy: IVec4
    get() = IVec4(y, x, x, y)
val IVec2.yxyx: IVec4
    get() = IVec4(y, x, y, x)
val IVec2.yxyy: IVec4
    get() = IVec4(y, x, y, y)
val IVec2.yyxx: IVec4
    get() = IVec4(y, y, x, x)
val IVec2.yyxy: IVec4
    get() = IVec4(y, y, x, y)
val IVec2.yyyx: IVec4
    get() = IVec4(y, y, y, x)
val IVec2.yyyy: IVec4
    get() = IVec4(y, y, y, y)