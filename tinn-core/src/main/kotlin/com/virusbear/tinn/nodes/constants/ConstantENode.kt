package com.virusbear.tinn.nodes.constants

import com.virusbear.tinn.nodes.*

class ConstantENode: BaseNode("E") {
    @Register
    companion object: NodeIdentifier("E", NodeCategory.Constant, ::ConstantENode)

    val e: Double by output("E", default = kotlin.math.E)

    override fun process() {}
}