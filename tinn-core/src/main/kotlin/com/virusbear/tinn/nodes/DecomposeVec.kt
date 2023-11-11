package com.virusbear.tinn.nodes

import com.virusbear.tinn.Context
import com.virusbear.tinn.math.*

class DecomposeVec2: BaseNode("Decompose Vec2", DecomposeVec2) {
    companion object : NodeIdentifier("Vec2", NodeCategory.Decompose, factory = { DecomposeVec2() })

    val input: Vec2 by input("Input", default = Vec2.ZERO)
    var x: Double by output("X")
    var y: Double by output("Y")

    override fun process(context: Context) {
        x = input.x
        y = input.y
    }
}

class DecomposeIVec2: BaseNode("Decompose IVec2", DecomposeIVec2) {
    companion object : NodeIdentifier("IVec2", NodeCategory.Decompose, factory = { DecomposeIVec2() })

    val input: IVec2 by input("Input", default = IVec2.ZERO)
    var x: Int by output("X")
    var y: Int by output("Y")

    override fun process(context: Context) {
        x = input.x
        y = input.y
    }
}

class DecomposeVec3: BaseNode("Decompose Vec3", DecomposeVec3) {
    companion object : NodeIdentifier("Vec3", NodeCategory.Decompose, factory = { DecomposeVec3() })

    val input: Vec3 by input("Input", default = Vec3.ZERO)
    var x: Double by output("X")
    var y: Double by output("Y")
    var z: Double by output("Z")

    override fun process(context: Context) {
        x = input.x
        y = input.y
        z = input.z
    }
}

class DecomposeIVec3: BaseNode("Decompose IVec3", DecomposeIVec3) {
    companion object : NodeIdentifier("IVec3", NodeCategory.Decompose, factory = { DecomposeIVec3() })

    val input: IVec3 by input("Input", default = IVec3.ZERO)
    var x: Int by output("X")
    var y: Int by output("Y")
    var z: Int by output("Z")

    override fun process(context: Context) {
        x = input.x
        y = input.y
        z = input.z
    }
}

class DecomposeVec4: BaseNode("Decompose Vec4", DecomposeVec4) {
    companion object : NodeIdentifier("Vec4", NodeCategory.Decompose, factory = { DecomposeVec4() })

    val input: Vec4 by input("Input", default = Vec4.ZERO)
    var x: Double by output("X")
    var y: Double by output("Y")
    var z: Double by output("Z")
    var w: Double by output("W")

    override fun process(context: Context) {
        x = input.x
        y = input.y
        z = input.z
        w = input.w
    }
}

class DecomposeIVec4: BaseNode("Decompose IVec4", DecomposeIVec4) {
    companion object : NodeIdentifier("IVec4", NodeCategory.Decompose, factory = { DecomposeIVec4() })

    val input: IVec4 by input("Input", default = IVec4.ZERO)
    var x: Int by output("X")
    var y: Int by output("Y")
    var z: Int by output("Z")
    var w: Int by output("W")

    override fun process(context: Context) {
        x = input.x
        y = input.y
        z = input.z
        w = input.w
    }
}