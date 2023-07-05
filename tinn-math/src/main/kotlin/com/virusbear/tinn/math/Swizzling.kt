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

//Vec4 -> Vec4
val Vec4.xxxx: Vec4
    get() = Vec4(x, x, x, x)
val Vec4.xxxy: Vec4
    get() = Vec4(x, x, x, y)
val Vec4.xxxz: Vec4
    get() = Vec4(x, x, x, z)
val Vec4.xxxw: Vec4
    get() = Vec4(x, x, x, w)
val Vec4.xxyx: Vec4
    get() = Vec4(x, x, y, x)
val Vec4.xxyy: Vec4
    get() = Vec4(x, x, y, y)
val Vec4.xxyz: Vec4
    get() = Vec4(x, x, y, z)
val Vec4.xxyw: Vec4
    get() = Vec4(x, x, y, w)
val Vec4.xxzx: Vec4
    get() = Vec4(x, x, z, x)
val Vec4.xxzy: Vec4
    get() = Vec4(x, x, z, y)
val Vec4.xxzz: Vec4
    get() = Vec4(x, x, z, z)
val Vec4.xxzw: Vec4
    get() = Vec4(x, x, z, w)
val Vec4.xxwx: Vec4
    get() = Vec4(x, x, w, x)
val Vec4.xxwy: Vec4
    get() = Vec4(x, x, w, y)
val Vec4.xxwz: Vec4
    get() = Vec4(x, x, w, z)
val Vec4.xxww: Vec4
    get() = Vec4(x, x, w, w)
val Vec4.xyxx: Vec4
    get() = Vec4(x, y, x, x)
val Vec4.xyxy: Vec4
    get() = Vec4(x, y, x, y)
val Vec4.xyxz: Vec4
    get() = Vec4(x, y, x, z)
val Vec4.xyxw: Vec4
    get() = Vec4(x, y, x, w)
val Vec4.xyyx: Vec4
    get() = Vec4(x, y, y, x)
val Vec4.xyyy: Vec4
    get() = Vec4(x, y, y, y)
val Vec4.xyyz: Vec4
    get() = Vec4(x, y, y, z)
val Vec4.xyyw: Vec4
    get() = Vec4(x, y, y, w)
val Vec4.xyzx: Vec4
    get() = Vec4(x, y, z, x)
val Vec4.xyzy: Vec4
    get() = Vec4(x, y, z, y)
val Vec4.xyzz: Vec4
    get() = Vec4(x, y, z, z)
val Vec4.xyzw: Vec4
    get() = Vec4(x, y, z, w)
val Vec4.xywx: Vec4
    get() = Vec4(x, y, w, x)
val Vec4.xywy: Vec4
    get() = Vec4(x, y, w, y)
val Vec4.xywz: Vec4
    get() = Vec4(x, y, w, z)
val Vec4.xyww: Vec4
    get() = Vec4(x, y, w, w)
val Vec4.xzxx: Vec4
    get() = Vec4(x, z, x, x)
val Vec4.xzxy: Vec4
    get() = Vec4(x, z, x, y)
val Vec4.xzxz: Vec4
    get() = Vec4(x, z, x, z)
val Vec4.xzxw: Vec4
    get() = Vec4(x, z, x, w)
val Vec4.xzyx: Vec4
    get() = Vec4(x, z, y, x)
val Vec4.xzyy: Vec4
    get() = Vec4(x, z, y, y)
val Vec4.xzyz: Vec4
    get() = Vec4(x, z, y, z)
val Vec4.xzyw: Vec4
    get() = Vec4(x, z, y, w)
val Vec4.xzzx: Vec4
    get() = Vec4(x, z, z, x)
val Vec4.xzzy: Vec4
    get() = Vec4(x, z, z, y)
val Vec4.xzzz: Vec4
    get() = Vec4(x, z, z, z)
val Vec4.xzzw: Vec4
    get() = Vec4(x, z, z, w)
val Vec4.xzwx: Vec4
    get() = Vec4(x, z, w, x)
val Vec4.xzwy: Vec4
    get() = Vec4(x, z, w, y)
val Vec4.xzwz: Vec4
    get() = Vec4(x, z, w, z)
val Vec4.xzww: Vec4
    get() = Vec4(x, z, w, w)
val Vec4.xwxx: Vec4
    get() = Vec4(x, w, x, x)
val Vec4.xwxy: Vec4
    get() = Vec4(x, w, x, y)
val Vec4.xwxz: Vec4
    get() = Vec4(x, w, x, z)
val Vec4.xwxw: Vec4
    get() = Vec4(x, w, x, w)
val Vec4.xwyx: Vec4
    get() = Vec4(x, w, y, x)
val Vec4.xwyy: Vec4
    get() = Vec4(x, w, y, y)
val Vec4.xwyz: Vec4
    get() = Vec4(x, w, y, z)
val Vec4.xwyw: Vec4
    get() = Vec4(x, w, y, w)
val Vec4.xwzx: Vec4
    get() = Vec4(x, w, z, x)
val Vec4.xwzy: Vec4
    get() = Vec4(x, w, z, y)
val Vec4.xwzz: Vec4
    get() = Vec4(x, w, z, z)
val Vec4.xwzw: Vec4
    get() = Vec4(x, w, z, w)
val Vec4.xwwx: Vec4
    get() = Vec4(x, w, w, x)
val Vec4.xwwy: Vec4
    get() = Vec4(x, w, w, y)
val Vec4.xwwz: Vec4
    get() = Vec4(x, w, w, z)
val Vec4.xwww: Vec4
    get() = Vec4(x, w, w, w)
val Vec4.yxxx: Vec4
    get() = Vec4(y, x, x, x)
val Vec4.yxxy: Vec4
    get() = Vec4(y, x, x, y)
val Vec4.yxxz: Vec4
    get() = Vec4(y, x, x, z)
val Vec4.yxxw: Vec4
    get() = Vec4(y, x, x, w)
val Vec4.yxyx: Vec4
    get() = Vec4(y, x, y, x)
val Vec4.yxyy: Vec4
    get() = Vec4(y, x, y, y)
val Vec4.yxyz: Vec4
    get() = Vec4(y, x, y, z)
val Vec4.yxyw: Vec4
    get() = Vec4(y, x, y, w)
val Vec4.yxzx: Vec4
    get() = Vec4(y, x, z, x)
val Vec4.yxzy: Vec4
    get() = Vec4(y, x, z, y)
val Vec4.yxzz: Vec4
    get() = Vec4(y, x, z, z)
val Vec4.yxzw: Vec4
    get() = Vec4(y, x, z, w)
val Vec4.yxwx: Vec4
    get() = Vec4(y, x, w, x)
val Vec4.yxwy: Vec4
    get() = Vec4(y, x, w, y)
val Vec4.yxwz: Vec4
    get() = Vec4(y, x, w, z)
val Vec4.yxww: Vec4
    get() = Vec4(y, x, w, w)
val Vec4.yyxx: Vec4
    get() = Vec4(y, y, x, x)
val Vec4.yyxy: Vec4
    get() = Vec4(y, y, x, y)
val Vec4.yyxz: Vec4
    get() = Vec4(y, y, x, z)
val Vec4.yyxw: Vec4
    get() = Vec4(y, y, x, w)
val Vec4.yyyx: Vec4
    get() = Vec4(y, y, y, x)
val Vec4.yyyy: Vec4
    get() = Vec4(y, y, y, y)
val Vec4.yyyz: Vec4
    get() = Vec4(y, y, y, z)
val Vec4.yyyw: Vec4
    get() = Vec4(y, y, y, w)
val Vec4.yyzx: Vec4
    get() = Vec4(y, y, z, x)
val Vec4.yyzy: Vec4
    get() = Vec4(y, y, z, y)
val Vec4.yyzz: Vec4
    get() = Vec4(y, y, z, z)
val Vec4.yyzw: Vec4
    get() = Vec4(y, y, z, w)
val Vec4.yywx: Vec4
    get() = Vec4(y, y, w, x)
val Vec4.yywy: Vec4
    get() = Vec4(y, y, w, y)
val Vec4.yywz: Vec4
    get() = Vec4(y, y, w, z)
val Vec4.yyww: Vec4
    get() = Vec4(y, y, w, w)
val Vec4.yzxx: Vec4
    get() = Vec4(y, z, x, x)
val Vec4.yzxy: Vec4
    get() = Vec4(y, z, x, y)
val Vec4.yzxz: Vec4
    get() = Vec4(y, z, x, z)
val Vec4.yzxw: Vec4
    get() = Vec4(y, z, x, w)
val Vec4.yzyx: Vec4
    get() = Vec4(y, z, y, x)
val Vec4.yzyy: Vec4
    get() = Vec4(y, z, y, y)
val Vec4.yzyz: Vec4
    get() = Vec4(y, z, y, z)
val Vec4.yzyw: Vec4
    get() = Vec4(y, z, y, w)
val Vec4.yzzx: Vec4
    get() = Vec4(y, z, z, x)
val Vec4.yzzy: Vec4
    get() = Vec4(y, z, z, y)
val Vec4.yzzz: Vec4
    get() = Vec4(y, z, z, z)
val Vec4.yzzw: Vec4
    get() = Vec4(y, z, z, w)
val Vec4.yzwx: Vec4
    get() = Vec4(y, z, w, x)
val Vec4.yzwy: Vec4
    get() = Vec4(y, z, w, y)
val Vec4.yzwz: Vec4
    get() = Vec4(y, z, w, z)
val Vec4.yzww: Vec4
    get() = Vec4(y, z, w, w)
val Vec4.ywxx: Vec4
    get() = Vec4(y, w, x, x)
val Vec4.ywxy: Vec4
    get() = Vec4(y, w, x, y)
val Vec4.ywxz: Vec4
    get() = Vec4(y, w, x, z)
val Vec4.ywxw: Vec4
    get() = Vec4(y, w, x, w)
val Vec4.ywyx: Vec4
    get() = Vec4(y, w, y, x)
val Vec4.ywyy: Vec4
    get() = Vec4(y, w, y, y)
val Vec4.ywyz: Vec4
    get() = Vec4(y, w, y, z)
val Vec4.ywyw: Vec4
    get() = Vec4(y, w, y, w)
val Vec4.ywzx: Vec4
    get() = Vec4(y, w, z, x)
val Vec4.ywzy: Vec4
    get() = Vec4(y, w, z, y)
val Vec4.ywzz: Vec4
    get() = Vec4(y, w, z, z)
val Vec4.ywzw: Vec4
    get() = Vec4(y, w, z, w)
val Vec4.ywwx: Vec4
    get() = Vec4(y, w, w, x)
val Vec4.ywwy: Vec4
    get() = Vec4(y, w, w, y)
val Vec4.ywwz: Vec4
    get() = Vec4(y, w, w, z)
val Vec4.ywww: Vec4
    get() = Vec4(y, w, w, w)
val Vec4.zxxx: Vec4
    get() = Vec4(z, x, x, x)
val Vec4.zxxy: Vec4
    get() = Vec4(z, x, x, y)
val Vec4.zxxz: Vec4
    get() = Vec4(z, x, x, z)
val Vec4.zxxw: Vec4
    get() = Vec4(z, x, x, w)
val Vec4.zxyx: Vec4
    get() = Vec4(z, x, y, x)
val Vec4.zxyy: Vec4
    get() = Vec4(z, x, y, y)
val Vec4.zxyz: Vec4
    get() = Vec4(z, x, y, z)
val Vec4.zxyw: Vec4
    get() = Vec4(z, x, y, w)
val Vec4.zxzx: Vec4
    get() = Vec4(z, x, z, x)
val Vec4.zxzy: Vec4
    get() = Vec4(z, x, z, y)
val Vec4.zxzz: Vec4
    get() = Vec4(z, x, z, z)
val Vec4.zxzw: Vec4
    get() = Vec4(z, x, z, w)
val Vec4.zxwx: Vec4
    get() = Vec4(z, x, w, x)
val Vec4.zxwy: Vec4
    get() = Vec4(z, x, w, y)
val Vec4.zxwz: Vec4
    get() = Vec4(z, x, w, z)
val Vec4.zxww: Vec4
    get() = Vec4(z, x, w, w)
val Vec4.zyxx: Vec4
    get() = Vec4(z, y, x, x)
val Vec4.zyxy: Vec4
    get() = Vec4(z, y, x, y)
val Vec4.zyxz: Vec4
    get() = Vec4(z, y, x, z)
val Vec4.zyxw: Vec4
    get() = Vec4(z, y, x, w)
val Vec4.zyyx: Vec4
    get() = Vec4(z, y, y, x)
val Vec4.zyyy: Vec4
    get() = Vec4(z, y, y, y)
val Vec4.zyyz: Vec4
    get() = Vec4(z, y, y, z)
val Vec4.zyyw: Vec4
    get() = Vec4(z, y, y, w)
val Vec4.zyzx: Vec4
    get() = Vec4(z, y, z, x)
val Vec4.zyzy: Vec4
    get() = Vec4(z, y, z, y)
val Vec4.zyzz: Vec4
    get() = Vec4(z, y, z, z)
val Vec4.zyzw: Vec4
    get() = Vec4(z, y, z, w)
val Vec4.zywx: Vec4
    get() = Vec4(z, y, w, x)
val Vec4.zywy: Vec4
    get() = Vec4(z, y, w, y)
val Vec4.zywz: Vec4
    get() = Vec4(z, y, w, z)
val Vec4.zyww: Vec4
    get() = Vec4(z, y, w, w)
val Vec4.zzxx: Vec4
    get() = Vec4(z, z, x, x)
val Vec4.zzxy: Vec4
    get() = Vec4(z, z, x, y)
val Vec4.zzxz: Vec4
    get() = Vec4(z, z, x, z)
val Vec4.zzxw: Vec4
    get() = Vec4(z, z, x, w)
val Vec4.zzyx: Vec4
    get() = Vec4(z, z, y, x)
val Vec4.zzyy: Vec4
    get() = Vec4(z, z, y, y)
val Vec4.zzyz: Vec4
    get() = Vec4(z, z, y, z)
val Vec4.zzyw: Vec4
    get() = Vec4(z, z, y, w)
val Vec4.zzzx: Vec4
    get() = Vec4(z, z, z, x)
val Vec4.zzzy: Vec4
    get() = Vec4(z, z, z, y)
val Vec4.zzzz: Vec4
    get() = Vec4(z, z, z, z)
val Vec4.zzzw: Vec4
    get() = Vec4(z, z, z, w)
val Vec4.zzwx: Vec4
    get() = Vec4(z, z, w, x)
val Vec4.zzwy: Vec4
    get() = Vec4(z, z, w, y)
val Vec4.zzwz: Vec4
    get() = Vec4(z, z, w, z)
val Vec4.zzww: Vec4
    get() = Vec4(z, z, w, w)
val Vec4.zwxx: Vec4
    get() = Vec4(z, w, x, x)
val Vec4.zwxy: Vec4
    get() = Vec4(z, w, x, y)
val Vec4.zwxz: Vec4
    get() = Vec4(z, w, x, z)
val Vec4.zwxw: Vec4
    get() = Vec4(z, w, x, w)
val Vec4.zwyx: Vec4
    get() = Vec4(z, w, y, x)
val Vec4.zwyy: Vec4
    get() = Vec4(z, w, y, y)
val Vec4.zwyz: Vec4
    get() = Vec4(z, w, y, z)
val Vec4.zwyw: Vec4
    get() = Vec4(z, w, y, w)
val Vec4.zwzx: Vec4
    get() = Vec4(z, w, z, x)
val Vec4.zwzy: Vec4
    get() = Vec4(z, w, z, y)
val Vec4.zwzz: Vec4
    get() = Vec4(z, w, z, z)
val Vec4.zwzw: Vec4
    get() = Vec4(z, w, z, w)
val Vec4.zwwx: Vec4
    get() = Vec4(z, w, w, x)
val Vec4.zwwy: Vec4
    get() = Vec4(z, w, w, y)
val Vec4.zwwz: Vec4
    get() = Vec4(z, w, w, z)
val Vec4.zwww: Vec4
    get() = Vec4(z, w, w, w)
val Vec4.wxxx: Vec4
    get() = Vec4(w, x, x, x)
val Vec4.wxxy: Vec4
    get() = Vec4(w, x, x, y)
val Vec4.wxxz: Vec4
    get() = Vec4(w, x, x, z)
val Vec4.wxxw: Vec4
    get() = Vec4(w, x, x, w)
val Vec4.wxyx: Vec4
    get() = Vec4(w, x, y, x)
val Vec4.wxyy: Vec4
    get() = Vec4(w, x, y, y)
val Vec4.wxyz: Vec4
    get() = Vec4(w, x, y, z)
val Vec4.wxyw: Vec4
    get() = Vec4(w, x, y, w)
val Vec4.wxzx: Vec4
    get() = Vec4(w, x, z, x)
val Vec4.wxzy: Vec4
    get() = Vec4(w, x, z, y)
val Vec4.wxzz: Vec4
    get() = Vec4(w, x, z, z)
val Vec4.wxzw: Vec4
    get() = Vec4(w, x, z, w)
val Vec4.wxwx: Vec4
    get() = Vec4(w, x, w, x)
val Vec4.wxwy: Vec4
    get() = Vec4(w, x, w, y)
val Vec4.wxwz: Vec4
    get() = Vec4(w, x, w, z)
val Vec4.wxww: Vec4
    get() = Vec4(w, x, w, w)
val Vec4.wyxx: Vec4
    get() = Vec4(w, y, x, x)
val Vec4.wyxy: Vec4
    get() = Vec4(w, y, x, y)
val Vec4.wyxz: Vec4
    get() = Vec4(w, y, x, z)
val Vec4.wyxw: Vec4
    get() = Vec4(w, y, x, w)
val Vec4.wyyx: Vec4
    get() = Vec4(w, y, y, x)
val Vec4.wyyy: Vec4
    get() = Vec4(w, y, y, y)
val Vec4.wyyz: Vec4
    get() = Vec4(w, y, y, z)
val Vec4.wyyw: Vec4
    get() = Vec4(w, y, y, w)
val Vec4.wyzx: Vec4
    get() = Vec4(w, y, z, x)
val Vec4.wyzy: Vec4
    get() = Vec4(w, y, z, y)
val Vec4.wyzz: Vec4
    get() = Vec4(w, y, z, z)
val Vec4.wyzw: Vec4
    get() = Vec4(w, y, z, w)
val Vec4.wywx: Vec4
    get() = Vec4(w, y, w, x)
val Vec4.wywy: Vec4
    get() = Vec4(w, y, w, y)
val Vec4.wywz: Vec4
    get() = Vec4(w, y, w, z)
val Vec4.wyww: Vec4
    get() = Vec4(w, y, w, w)
val Vec4.wzxx: Vec4
    get() = Vec4(w, z, x, x)
val Vec4.wzxy: Vec4
    get() = Vec4(w, z, x, y)
val Vec4.wzxz: Vec4
    get() = Vec4(w, z, x, z)
val Vec4.wzxw: Vec4
    get() = Vec4(w, z, x, w)
val Vec4.wzyx: Vec4
    get() = Vec4(w, z, y, x)
val Vec4.wzyy: Vec4
    get() = Vec4(w, z, y, y)
val Vec4.wzyz: Vec4
    get() = Vec4(w, z, y, z)
val Vec4.wzyw: Vec4
    get() = Vec4(w, z, y, w)
val Vec4.wzzx: Vec4
    get() = Vec4(w, z, z, x)
val Vec4.wzzy: Vec4
    get() = Vec4(w, z, z, y)
val Vec4.wzzz: Vec4
    get() = Vec4(w, z, z, z)
val Vec4.wzzw: Vec4
    get() = Vec4(w, z, z, w)
val Vec4.wzwx: Vec4
    get() = Vec4(w, z, w, x)
val Vec4.wzwy: Vec4
    get() = Vec4(w, z, w, y)
val Vec4.wzwz: Vec4
    get() = Vec4(w, z, w, z)
val Vec4.wzww: Vec4
    get() = Vec4(w, z, w, w)
val Vec4.wwxx: Vec4
    get() = Vec4(w, w, x, x)
val Vec4.wwxy: Vec4
    get() = Vec4(w, w, x, y)
val Vec4.wwxz: Vec4
    get() = Vec4(w, w, x, z)
val Vec4.wwxw: Vec4
    get() = Vec4(w, w, x, w)
val Vec4.wwyx: Vec4
    get() = Vec4(w, w, y, x)
val Vec4.wwyy: Vec4
    get() = Vec4(w, w, y, y)
val Vec4.wwyz: Vec4
    get() = Vec4(w, w, y, z)
val Vec4.wwyw: Vec4
    get() = Vec4(w, w, y, w)
val Vec4.wwzx: Vec4
    get() = Vec4(w, w, z, x)
val Vec4.wwzy: Vec4
    get() = Vec4(w, w, z, y)
val Vec4.wwzz: Vec4
    get() = Vec4(w, w, z, z)
val Vec4.wwzw: Vec4
    get() = Vec4(w, w, z, w)
val Vec4.wwwx: Vec4
    get() = Vec4(w, w, w, x)
val Vec4.wwwy: Vec4
    get() = Vec4(w, w, w, y)
val Vec4.wwwz: Vec4
    get() = Vec4(w, w, w, z)
val Vec4.wwww: Vec4
    get() = Vec4(w, w, w, w)

//IVec4 -> IVec4
val IVec4.xxxx: IVec4
    get() = IVec4(x, x, x, x)
val IVec4.xxxy: IVec4
    get() = IVec4(x, x, x, y)
val IVec4.xxxz: IVec4
    get() = IVec4(x, x, x, z)
val IVec4.xxxw: IVec4
    get() = IVec4(x, x, x, w)
val IVec4.xxyx: IVec4
    get() = IVec4(x, x, y, x)
val IVec4.xxyy: IVec4
    get() = IVec4(x, x, y, y)
val IVec4.xxyz: IVec4
    get() = IVec4(x, x, y, z)
val IVec4.xxyw: IVec4
    get() = IVec4(x, x, y, w)
val IVec4.xxzx: IVec4
    get() = IVec4(x, x, z, x)
val IVec4.xxzy: IVec4
    get() = IVec4(x, x, z, y)
val IVec4.xxzz: IVec4
    get() = IVec4(x, x, z, z)
val IVec4.xxzw: IVec4
    get() = IVec4(x, x, z, w)
val IVec4.xxwx: IVec4
    get() = IVec4(x, x, w, x)
val IVec4.xxwy: IVec4
    get() = IVec4(x, x, w, y)
val IVec4.xxwz: IVec4
    get() = IVec4(x, x, w, z)
val IVec4.xxww: IVec4
    get() = IVec4(x, x, w, w)
val IVec4.xyxx: IVec4
    get() = IVec4(x, y, x, x)
val IVec4.xyxy: IVec4
    get() = IVec4(x, y, x, y)
val IVec4.xyxz: IVec4
    get() = IVec4(x, y, x, z)
val IVec4.xyxw: IVec4
    get() = IVec4(x, y, x, w)
val IVec4.xyyx: IVec4
    get() = IVec4(x, y, y, x)
val IVec4.xyyy: IVec4
    get() = IVec4(x, y, y, y)
val IVec4.xyyz: IVec4
    get() = IVec4(x, y, y, z)
val IVec4.xyyw: IVec4
    get() = IVec4(x, y, y, w)
val IVec4.xyzx: IVec4
    get() = IVec4(x, y, z, x)
val IVec4.xyzy: IVec4
    get() = IVec4(x, y, z, y)
val IVec4.xyzz: IVec4
    get() = IVec4(x, y, z, z)
val IVec4.xyzw: IVec4
    get() = IVec4(x, y, z, w)
val IVec4.xywx: IVec4
    get() = IVec4(x, y, w, x)
val IVec4.xywy: IVec4
    get() = IVec4(x, y, w, y)
val IVec4.xywz: IVec4
    get() = IVec4(x, y, w, z)
val IVec4.xyww: IVec4
    get() = IVec4(x, y, w, w)
val IVec4.xzxx: IVec4
    get() = IVec4(x, z, x, x)
val IVec4.xzxy: IVec4
    get() = IVec4(x, z, x, y)
val IVec4.xzxz: IVec4
    get() = IVec4(x, z, x, z)
val IVec4.xzxw: IVec4
    get() = IVec4(x, z, x, w)
val IVec4.xzyx: IVec4
    get() = IVec4(x, z, y, x)
val IVec4.xzyy: IVec4
    get() = IVec4(x, z, y, y)
val IVec4.xzyz: IVec4
    get() = IVec4(x, z, y, z)
val IVec4.xzyw: IVec4
    get() = IVec4(x, z, y, w)
val IVec4.xzzx: IVec4
    get() = IVec4(x, z, z, x)
val IVec4.xzzy: IVec4
    get() = IVec4(x, z, z, y)
val IVec4.xzzz: IVec4
    get() = IVec4(x, z, z, z)
val IVec4.xzzw: IVec4
    get() = IVec4(x, z, z, w)
val IVec4.xzwx: IVec4
    get() = IVec4(x, z, w, x)
val IVec4.xzwy: IVec4
    get() = IVec4(x, z, w, y)
val IVec4.xzwz: IVec4
    get() = IVec4(x, z, w, z)
val IVec4.xzww: IVec4
    get() = IVec4(x, z, w, w)
val IVec4.xwxx: IVec4
    get() = IVec4(x, w, x, x)
val IVec4.xwxy: IVec4
    get() = IVec4(x, w, x, y)
val IVec4.xwxz: IVec4
    get() = IVec4(x, w, x, z)
val IVec4.xwxw: IVec4
    get() = IVec4(x, w, x, w)
val IVec4.xwyx: IVec4
    get() = IVec4(x, w, y, x)
val IVec4.xwyy: IVec4
    get() = IVec4(x, w, y, y)
val IVec4.xwyz: IVec4
    get() = IVec4(x, w, y, z)
val IVec4.xwyw: IVec4
    get() = IVec4(x, w, y, w)
val IVec4.xwzx: IVec4
    get() = IVec4(x, w, z, x)
val IVec4.xwzy: IVec4
    get() = IVec4(x, w, z, y)
val IVec4.xwzz: IVec4
    get() = IVec4(x, w, z, z)
val IVec4.xwzw: IVec4
    get() = IVec4(x, w, z, w)
val IVec4.xwwx: IVec4
    get() = IVec4(x, w, w, x)
val IVec4.xwwy: IVec4
    get() = IVec4(x, w, w, y)
val IVec4.xwwz: IVec4
    get() = IVec4(x, w, w, z)
val IVec4.xwww: IVec4
    get() = IVec4(x, w, w, w)
val IVec4.yxxx: IVec4
    get() = IVec4(y, x, x, x)
val IVec4.yxxy: IVec4
    get() = IVec4(y, x, x, y)
val IVec4.yxxz: IVec4
    get() = IVec4(y, x, x, z)
val IVec4.yxxw: IVec4
    get() = IVec4(y, x, x, w)
val IVec4.yxyx: IVec4
    get() = IVec4(y, x, y, x)
val IVec4.yxyy: IVec4
    get() = IVec4(y, x, y, y)
val IVec4.yxyz: IVec4
    get() = IVec4(y, x, y, z)
val IVec4.yxyw: IVec4
    get() = IVec4(y, x, y, w)
val IVec4.yxzx: IVec4
    get() = IVec4(y, x, z, x)
val IVec4.yxzy: IVec4
    get() = IVec4(y, x, z, y)
val IVec4.yxzz: IVec4
    get() = IVec4(y, x, z, z)
val IVec4.yxzw: IVec4
    get() = IVec4(y, x, z, w)
val IVec4.yxwx: IVec4
    get() = IVec4(y, x, w, x)
val IVec4.yxwy: IVec4
    get() = IVec4(y, x, w, y)
val IVec4.yxwz: IVec4
    get() = IVec4(y, x, w, z)
val IVec4.yxww: IVec4
    get() = IVec4(y, x, w, w)
val IVec4.yyxx: IVec4
    get() = IVec4(y, y, x, x)
val IVec4.yyxy: IVec4
    get() = IVec4(y, y, x, y)
val IVec4.yyxz: IVec4
    get() = IVec4(y, y, x, z)
val IVec4.yyxw: IVec4
    get() = IVec4(y, y, x, w)
val IVec4.yyyx: IVec4
    get() = IVec4(y, y, y, x)
val IVec4.yyyy: IVec4
    get() = IVec4(y, y, y, y)
val IVec4.yyyz: IVec4
    get() = IVec4(y, y, y, z)
val IVec4.yyyw: IVec4
    get() = IVec4(y, y, y, w)
val IVec4.yyzx: IVec4
    get() = IVec4(y, y, z, x)
val IVec4.yyzy: IVec4
    get() = IVec4(y, y, z, y)
val IVec4.yyzz: IVec4
    get() = IVec4(y, y, z, z)
val IVec4.yyzw: IVec4
    get() = IVec4(y, y, z, w)
val IVec4.yywx: IVec4
    get() = IVec4(y, y, w, x)
val IVec4.yywy: IVec4
    get() = IVec4(y, y, w, y)
val IVec4.yywz: IVec4
    get() = IVec4(y, y, w, z)
val IVec4.yyww: IVec4
    get() = IVec4(y, y, w, w)
val IVec4.yzxx: IVec4
    get() = IVec4(y, z, x, x)
val IVec4.yzxy: IVec4
    get() = IVec4(y, z, x, y)
val IVec4.yzxz: IVec4
    get() = IVec4(y, z, x, z)
val IVec4.yzxw: IVec4
    get() = IVec4(y, z, x, w)
val IVec4.yzyx: IVec4
    get() = IVec4(y, z, y, x)
val IVec4.yzyy: IVec4
    get() = IVec4(y, z, y, y)
val IVec4.yzyz: IVec4
    get() = IVec4(y, z, y, z)
val IVec4.yzyw: IVec4
    get() = IVec4(y, z, y, w)
val IVec4.yzzx: IVec4
    get() = IVec4(y, z, z, x)
val IVec4.yzzy: IVec4
    get() = IVec4(y, z, z, y)
val IVec4.yzzz: IVec4
    get() = IVec4(y, z, z, z)
val IVec4.yzzw: IVec4
    get() = IVec4(y, z, z, w)
val IVec4.yzwx: IVec4
    get() = IVec4(y, z, w, x)
val IVec4.yzwy: IVec4
    get() = IVec4(y, z, w, y)
val IVec4.yzwz: IVec4
    get() = IVec4(y, z, w, z)
val IVec4.yzww: IVec4
    get() = IVec4(y, z, w, w)
val IVec4.ywxx: IVec4
    get() = IVec4(y, w, x, x)
val IVec4.ywxy: IVec4
    get() = IVec4(y, w, x, y)
val IVec4.ywxz: IVec4
    get() = IVec4(y, w, x, z)
val IVec4.ywxw: IVec4
    get() = IVec4(y, w, x, w)
val IVec4.ywyx: IVec4
    get() = IVec4(y, w, y, x)
val IVec4.ywyy: IVec4
    get() = IVec4(y, w, y, y)
val IVec4.ywyz: IVec4
    get() = IVec4(y, w, y, z)
val IVec4.ywyw: IVec4
    get() = IVec4(y, w, y, w)
val IVec4.ywzx: IVec4
    get() = IVec4(y, w, z, x)
val IVec4.ywzy: IVec4
    get() = IVec4(y, w, z, y)
val IVec4.ywzz: IVec4
    get() = IVec4(y, w, z, z)
val IVec4.ywzw: IVec4
    get() = IVec4(y, w, z, w)
val IVec4.ywwx: IVec4
    get() = IVec4(y, w, w, x)
val IVec4.ywwy: IVec4
    get() = IVec4(y, w, w, y)
val IVec4.ywwz: IVec4
    get() = IVec4(y, w, w, z)
val IVec4.ywww: IVec4
    get() = IVec4(y, w, w, w)
val IVec4.zxxx: IVec4
    get() = IVec4(z, x, x, x)
val IVec4.zxxy: IVec4
    get() = IVec4(z, x, x, y)
val IVec4.zxxz: IVec4
    get() = IVec4(z, x, x, z)
val IVec4.zxxw: IVec4
    get() = IVec4(z, x, x, w)
val IVec4.zxyx: IVec4
    get() = IVec4(z, x, y, x)
val IVec4.zxyy: IVec4
    get() = IVec4(z, x, y, y)
val IVec4.zxyz: IVec4
    get() = IVec4(z, x, y, z)
val IVec4.zxyw: IVec4
    get() = IVec4(z, x, y, w)
val IVec4.zxzx: IVec4
    get() = IVec4(z, x, z, x)
val IVec4.zxzy: IVec4
    get() = IVec4(z, x, z, y)
val IVec4.zxzz: IVec4
    get() = IVec4(z, x, z, z)
val IVec4.zxzw: IVec4
    get() = IVec4(z, x, z, w)
val IVec4.zxwx: IVec4
    get() = IVec4(z, x, w, x)
val IVec4.zxwy: IVec4
    get() = IVec4(z, x, w, y)
val IVec4.zxwz: IVec4
    get() = IVec4(z, x, w, z)
val IVec4.zxww: IVec4
    get() = IVec4(z, x, w, w)
val IVec4.zyxx: IVec4
    get() = IVec4(z, y, x, x)
val IVec4.zyxy: IVec4
    get() = IVec4(z, y, x, y)
val IVec4.zyxz: IVec4
    get() = IVec4(z, y, x, z)
val IVec4.zyxw: IVec4
    get() = IVec4(z, y, x, w)
val IVec4.zyyx: IVec4
    get() = IVec4(z, y, y, x)
val IVec4.zyyy: IVec4
    get() = IVec4(z, y, y, y)
val IVec4.zyyz: IVec4
    get() = IVec4(z, y, y, z)
val IVec4.zyyw: IVec4
    get() = IVec4(z, y, y, w)
val IVec4.zyzx: IVec4
    get() = IVec4(z, y, z, x)
val IVec4.zyzy: IVec4
    get() = IVec4(z, y, z, y)
val IVec4.zyzz: IVec4
    get() = IVec4(z, y, z, z)
val IVec4.zyzw: IVec4
    get() = IVec4(z, y, z, w)
val IVec4.zywx: IVec4
    get() = IVec4(z, y, w, x)
val IVec4.zywy: IVec4
    get() = IVec4(z, y, w, y)
val IVec4.zywz: IVec4
    get() = IVec4(z, y, w, z)
val IVec4.zyww: IVec4
    get() = IVec4(z, y, w, w)
val IVec4.zzxx: IVec4
    get() = IVec4(z, z, x, x)
val IVec4.zzxy: IVec4
    get() = IVec4(z, z, x, y)
val IVec4.zzxz: IVec4
    get() = IVec4(z, z, x, z)
val IVec4.zzxw: IVec4
    get() = IVec4(z, z, x, w)
val IVec4.zzyx: IVec4
    get() = IVec4(z, z, y, x)
val IVec4.zzyy: IVec4
    get() = IVec4(z, z, y, y)
val IVec4.zzyz: IVec4
    get() = IVec4(z, z, y, z)
val IVec4.zzyw: IVec4
    get() = IVec4(z, z, y, w)
val IVec4.zzzx: IVec4
    get() = IVec4(z, z, z, x)
val IVec4.zzzy: IVec4
    get() = IVec4(z, z, z, y)
val IVec4.zzzz: IVec4
    get() = IVec4(z, z, z, z)
val IVec4.zzzw: IVec4
    get() = IVec4(z, z, z, w)
val IVec4.zzwx: IVec4
    get() = IVec4(z, z, w, x)
val IVec4.zzwy: IVec4
    get() = IVec4(z, z, w, y)
val IVec4.zzwz: IVec4
    get() = IVec4(z, z, w, z)
val IVec4.zzww: IVec4
    get() = IVec4(z, z, w, w)
val IVec4.zwxx: IVec4
    get() = IVec4(z, w, x, x)
val IVec4.zwxy: IVec4
    get() = IVec4(z, w, x, y)
val IVec4.zwxz: IVec4
    get() = IVec4(z, w, x, z)
val IVec4.zwxw: IVec4
    get() = IVec4(z, w, x, w)
val IVec4.zwyx: IVec4
    get() = IVec4(z, w, y, x)
val IVec4.zwyy: IVec4
    get() = IVec4(z, w, y, y)
val IVec4.zwyz: IVec4
    get() = IVec4(z, w, y, z)
val IVec4.zwyw: IVec4
    get() = IVec4(z, w, y, w)
val IVec4.zwzx: IVec4
    get() = IVec4(z, w, z, x)
val IVec4.zwzy: IVec4
    get() = IVec4(z, w, z, y)
val IVec4.zwzz: IVec4
    get() = IVec4(z, w, z, z)
val IVec4.zwzw: IVec4
    get() = IVec4(z, w, z, w)
val IVec4.zwwx: IVec4
    get() = IVec4(z, w, w, x)
val IVec4.zwwy: IVec4
    get() = IVec4(z, w, w, y)
val IVec4.zwwz: IVec4
    get() = IVec4(z, w, w, z)
val IVec4.zwww: IVec4
    get() = IVec4(z, w, w, w)
val IVec4.wxxx: IVec4
    get() = IVec4(w, x, x, x)
val IVec4.wxxy: IVec4
    get() = IVec4(w, x, x, y)
val IVec4.wxxz: IVec4
    get() = IVec4(w, x, x, z)
val IVec4.wxxw: IVec4
    get() = IVec4(w, x, x, w)
val IVec4.wxyx: IVec4
    get() = IVec4(w, x, y, x)
val IVec4.wxyy: IVec4
    get() = IVec4(w, x, y, y)
val IVec4.wxyz: IVec4
    get() = IVec4(w, x, y, z)
val IVec4.wxyw: IVec4
    get() = IVec4(w, x, y, w)
val IVec4.wxzx: IVec4
    get() = IVec4(w, x, z, x)
val IVec4.wxzy: IVec4
    get() = IVec4(w, x, z, y)
val IVec4.wxzz: IVec4
    get() = IVec4(w, x, z, z)
val IVec4.wxzw: IVec4
    get() = IVec4(w, x, z, w)
val IVec4.wxwx: IVec4
    get() = IVec4(w, x, w, x)
val IVec4.wxwy: IVec4
    get() = IVec4(w, x, w, y)
val IVec4.wxwz: IVec4
    get() = IVec4(w, x, w, z)
val IVec4.wxww: IVec4
    get() = IVec4(w, x, w, w)
val IVec4.wyxx: IVec4
    get() = IVec4(w, y, x, x)
val IVec4.wyxy: IVec4
    get() = IVec4(w, y, x, y)
val IVec4.wyxz: IVec4
    get() = IVec4(w, y, x, z)
val IVec4.wyxw: IVec4
    get() = IVec4(w, y, x, w)
val IVec4.wyyx: IVec4
    get() = IVec4(w, y, y, x)
val IVec4.wyyy: IVec4
    get() = IVec4(w, y, y, y)
val IVec4.wyyz: IVec4
    get() = IVec4(w, y, y, z)
val IVec4.wyyw: IVec4
    get() = IVec4(w, y, y, w)
val IVec4.wyzx: IVec4
    get() = IVec4(w, y, z, x)
val IVec4.wyzy: IVec4
    get() = IVec4(w, y, z, y)
val IVec4.wyzz: IVec4
    get() = IVec4(w, y, z, z)
val IVec4.wyzw: IVec4
    get() = IVec4(w, y, z, w)
val IVec4.wywx: IVec4
    get() = IVec4(w, y, w, x)
val IVec4.wywy: IVec4
    get() = IVec4(w, y, w, y)
val IVec4.wywz: IVec4
    get() = IVec4(w, y, w, z)
val IVec4.wyww: IVec4
    get() = IVec4(w, y, w, w)
val IVec4.wzxx: IVec4
    get() = IVec4(w, z, x, x)
val IVec4.wzxy: IVec4
    get() = IVec4(w, z, x, y)
val IVec4.wzxz: IVec4
    get() = IVec4(w, z, x, z)
val IVec4.wzxw: IVec4
    get() = IVec4(w, z, x, w)
val IVec4.wzyx: IVec4
    get() = IVec4(w, z, y, x)
val IVec4.wzyy: IVec4
    get() = IVec4(w, z, y, y)
val IVec4.wzyz: IVec4
    get() = IVec4(w, z, y, z)
val IVec4.wzyw: IVec4
    get() = IVec4(w, z, y, w)
val IVec4.wzzx: IVec4
    get() = IVec4(w, z, z, x)
val IVec4.wzzy: IVec4
    get() = IVec4(w, z, z, y)
val IVec4.wzzz: IVec4
    get() = IVec4(w, z, z, z)
val IVec4.wzzw: IVec4
    get() = IVec4(w, z, z, w)
val IVec4.wzwx: IVec4
    get() = IVec4(w, z, w, x)
val IVec4.wzwy: IVec4
    get() = IVec4(w, z, w, y)
val IVec4.wzwz: IVec4
    get() = IVec4(w, z, w, z)
val IVec4.wzww: IVec4
    get() = IVec4(w, z, w, w)
val IVec4.wwxx: IVec4
    get() = IVec4(w, w, x, x)
val IVec4.wwxy: IVec4
    get() = IVec4(w, w, x, y)
val IVec4.wwxz: IVec4
    get() = IVec4(w, w, x, z)
val IVec4.wwxw: IVec4
    get() = IVec4(w, w, x, w)
val IVec4.wwyx: IVec4
    get() = IVec4(w, w, y, x)
val IVec4.wwyy: IVec4
    get() = IVec4(w, w, y, y)
val IVec4.wwyz: IVec4
    get() = IVec4(w, w, y, z)
val IVec4.wwyw: IVec4
    get() = IVec4(w, w, y, w)
val IVec4.wwzx: IVec4
    get() = IVec4(w, w, z, x)
val IVec4.wwzy: IVec4
    get() = IVec4(w, w, z, y)
val IVec4.wwzz: IVec4
    get() = IVec4(w, w, z, z)
val IVec4.wwzw: IVec4
    get() = IVec4(w, w, z, w)
val IVec4.wwwx: IVec4
    get() = IVec4(w, w, w, x)
val IVec4.wwwy: IVec4
    get() = IVec4(w, w, w, y)
val IVec4.wwwz: IVec4
    get() = IVec4(w, w, w, z)
val IVec4.wwww: IVec4
    get() = IVec4(w, w, w, w)