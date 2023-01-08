package com.virusbear.tinn.nodes

import com.virusbear.tinn.math.*
import com.virusbear.tinn.Context

class ComposeVec2: BaseNode("Compose Vec2", ComposeVec2) {
    @Register
    companion object: NodeIdentifier("Vec2", NodeCategory.Compose, factory = { ComposeVec2() })

    val x: Double by input("X", default = 0.0)
    val y: Double by input("Y", default = 0.0)
    var output: Vec2 by output("Output")

    override fun process(context: Context) {
        output = Vec2(x, y)
    }
}

class ComposeIVec2: BaseNode("Compose IVec2", ComposeIVec2) {
    @Register
    companion object: NodeIdentifier("IVec2", NodeCategory.Compose, factory = { ComposeVec2() })

    val x: Int by input("X", default = 0)
    val y: Int by input("Y", default = 0)
    var output: IVec2 by output("Output")

    override fun process(context: Context) {
        output = IVec2(x, y)
    }
}

class ComposeVec3: BaseNode("Compose Vec3", ComposeVec3) {
    @Register
    companion object: NodeIdentifier("Vec3", NodeCategory.Compose, factory = { ComposeVec3() })

    val x: Double by input("X", default = 0.0)
    val y: Double by input("Y", default = 0.0)
    val z: Double by input("Z", default = 0.0)
    var output: Vec3 by output("Output")

    override fun process(context: Context) {
        output = Vec3(x, y, z)
    }
}

class ComposeIVec3: BaseNode("Compose IVec3", ComposeIVec3) {
    @Register
    companion object: NodeIdentifier("IVec3", NodeCategory.Compose, factory = { ComposeIVec3() })

    val x: Int by input("X", default = 0)
    val y: Int by input("Y", default = 0)
    val z: Int by input("Z", default = 0)
    var output: IVec3 by output("Output")

    override fun process(context: Context) {
        output = IVec3(x, y, z)
    }
}

class ComposeVec4: BaseNode("Compose Vec4", ComposeVec4) {
    @Register
    companion object: NodeIdentifier("Vec4", NodeCategory.Compose, factory = { ComposeVec4() })

    val x: Double by input("X", default = 0.0)
    val y: Double by input("Y", default = 0.0)
    val z: Double by input("Z", default = 0.0)
    val w: Double by input("W", default = 0.0)
    var output: Vec4 by output("Output")

    override fun process(context: Context) {
        output = Vec4(x, y, z, w)
    }
}

class ComposeIVec4: BaseNode("Compose IVec4", ComposeIVec4) {
    @Register
    companion object: NodeIdentifier("IVec4", NodeCategory.Compose, factory = { ComposeIVec4() })

    val x: Int by input("X", default = 0)
    val y: Int by input("Y", default = 0)
    val z: Int by input("Z", default = 0)
    val w: Int by input("W", default = 0)
    var output: IVec4 by output("Output")

    override fun process(context: Context) {
        output = IVec4(x, y, z, w)
    }
}