package com.virusbear.tinn.math

// Vec2 -> Vec2


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