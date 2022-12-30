package com.virusbear.tinn.nodes.convert

import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.Register
import kotlin.math.PI

class DegreesToRadiansNode: BaseNode("Deg to Rad", DegreesToRadiansNode) {
    @Register
    companion object: NodeIdentifier("Deg to Rad", NodeCategory.Convert, { DegreesToRadiansNode() })

    val degrees: Double by input("Degrees", default = 0.0)
    var radians: Double by output("Radians", default = 0.0)

    override fun process() {
        radians = degrees * PI / 180
    }
}