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

//TODO: Vec3 -> Vec3
//TODO: IVec3 -> IVec3
