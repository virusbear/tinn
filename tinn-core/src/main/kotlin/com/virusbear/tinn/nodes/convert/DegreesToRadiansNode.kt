package com.virusbear.tinn.nodes.convert

import com.virusbear.tinn.nodes.*
import com.virusbear.tinn.Context
import kotlin.math.PI

class DegreesToRadiansNode: BaseNode("Deg to Rad", DegreesToRadiansNode) {
    @Register
    companion object: NodeIdentifier("Deg to Rad", NodeCategory.Convert, factory = { DegreesToRadiansNode() })

    val degrees: Double by input("Degrees", default = 0.0)
    var radians: Double by output("Radians", default = 0.0)

    override fun process(context: Context) {
        radians = degrees * PI / 180
    }
}