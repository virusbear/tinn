package com.virusbear.tinn.nodes.constants

import com.virusbear.tinn.nodes.*
import com.virusbear.tinn.Context

class ConstantENode: BaseNode("E", ConstantENode) {
    @Register
    companion object: NodeIdentifier("E", NodeCategory.Constant, factory = { ConstantENode() })

    val e: Double by output("E", default = kotlin.math.E)

    override fun process(context: Context) {}
}