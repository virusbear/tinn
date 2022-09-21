package com.virusbear.tinn.math

// Vec2 -> Vec2
val Vec2.xx: Vec2
    get() = Vec2(x, x)
val Vec2.yy: Vec2
    get() = Vec2(y, y)
val Vec2.xy: Vec2
    get() = Vec2(x, y)
val Vec2.yx: Vec2
    get() = Vec2(y, x)

// IVec2 -> IVec2
val IVec2.xx: IVec2
    get() = IVec2(x, x)
val IVec2.yy: IVec2
    get() = IVec2(y, y)
val IVec2.xy: IVec2
    get() = IVec2(x, y)
val IVec2.yx: IVec2
    get() = IVec2(y, x)

// Vec2 -> Vec3
val Vec2.xxx: Vec3
    get() = Vec3(x, x, x)
val Vec2.xxy: Vec3
    get() = Vec3(x, x, y)
val Vec2.xyx: Vec3
    get() = Vec3(x, y, x)
val Vec2.xyy: Vec3
    get() = Vec3(x, y, y)
val Vec2.yxx: Vec3
    get() = Vec3(y, x, x)
val Vec2.yxy: Vec3
    get() = Vec3(y, x, y)
val Vec2.yyx: Vec3
    get() = Vec3(y, y, x)
val Vec2.yyy: Vec3
    get() = Vec3(y, y, y)
val Vec3.xy0: Vec3
    get() = Vec3(x, y, 0.0)

// IVec2 -> IVec3
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
val IVec3.xy0: IVec3
    get() = IVec3(x, y, 0)

//Vec3 -> Vec2
val Vec3.xx: Vec2
    get() = Vec2(x, x)
val Vec3.yy: Vec2
    get() = Vec2(y, y)
val Vec3.zz: Vec2
    get() = Vec2(z, z)
val Vec3.xy: Vec2
    get() = Vec2(x, y)
val Vec3.yx: Vec2
    get() = Vec2(y, x)
val Vec3.zx: Vec2
    get() = Vec2(z, x)
val Vec3.xz: Vec2
    get() = Vec2(x, z)
val Vec3.zy: Vec2
    get() = Vec2(x, y)
val Vec3.yz: Vec2
    get() = Vec2(y, z)

//IVec3 -> IVec2
val IVec3.xx: IVec2
    get() = IVec2(x, x)
val IVec3.yy: IVec2
    get() = IVec2(y, y)
val IVec3.zz: IVec2
    get() = IVec2(z, z)
val IVec3.xy: IVec2
    get() = IVec2(x, y)
val IVec3.yx: IVec2
    get() = IVec2(y, x)
val IVec3.zx: IVec2
    get() = IVec2(z, x)
val IVec3.xz: IVec2
    get() = IVec2(x, z)
val IVec3.zy: IVec2
    get() = IVec2(x, y)
val IVec3.yz: IVec2
    get() = IVec2(y, z)

//Vec3 -> Vec3
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

//IVec3 -> IVec3
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