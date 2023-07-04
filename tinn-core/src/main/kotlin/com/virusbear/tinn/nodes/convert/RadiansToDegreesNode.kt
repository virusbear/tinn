package com.virusbear.tinn.nodes.convert

import com.virusbear.tinn.Context
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import kotlin.math.PI

class RadiansToDegreesNode: BaseNode("Rad to Deg", RadiansToDegreesNode) {
    companion object: NodeIdentifier("Rad to Deg", NodeCategory.Convert, factory = { RadiansToDegreesNode() })

    val radians: Double by input("Radians", default = 0.0)
    var degrees: Double by output("Degrees", default = 0.0)

    override fun process(context: Context) {
        degrees = radians * 180 / PI
    }
}