package com.virusbear.tinn.nodes.math.convert

import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.Register
import kotlin.math.PI

class RadiansToDegreesNode: BaseNode("Rad to Deg") {
    @Register
    companion object: NodeIdentifier("Rad to Deg", NodeCategory.Convert, ::RadiansToDegreesNode)

    val radians: Double by input("Radians", default = 0.0)
    var degrees: Double by output("Degrees", default = 0.0)

    override fun process() {
        degrees = radians * 180 / PI
    }
}

class DegreesToRadiansNode: BaseNode("Deg to Rad") {
    @Register
    companion object: NodeIdentifier("Deg to Rad", NodeCategory.Convert, ::DegreesToRadiansNode)

    val degrees: Double by input("Degrees", default = 0.0)
    var radians: Double by output("Radians", default = 0.0)

    override fun process() {
        radians = degrees * PI / 180
    }
}