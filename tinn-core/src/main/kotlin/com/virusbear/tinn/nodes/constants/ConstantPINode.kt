package com.virusbear.tinn.nodes.constants

import com.virusbear.tinn.nodes.*

class ConstantPINode: BaseNode("PI", ConstantPINode) {
    @Register
    companion object: NodeIdentifier("PI", NodeCategory.Constant, { ConstantPINode() })

    val pi: Double by output("PI", default = kotlin.math.PI)

    override fun process() {}
}