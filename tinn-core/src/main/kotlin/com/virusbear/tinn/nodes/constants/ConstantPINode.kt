package com.virusbear.tinn.nodes.constants

import com.virusbear.tinn.nodes.*
import com.virusbear.tinn.Context

class ConstantPINode: BaseNode("PI", ConstantPINode) {
    @Register
    companion object: NodeIdentifier("PI", NodeCategory.Constant, factory = { ConstantPINode() })

    val pi: Double by output("PI", default = kotlin.math.PI)

    override fun process(context: Context) {}
}