package com.virusbear.tinn.nodes.math.constants

import com.virusbear.tinn.nodes.*

class ConstantPINode: BaseNode("PI") {
    @Register
    companion object: NodeIdentifier("PI", NodeCategory.Constant, ::ConstantPINode)

    val pi: Double by output("PI", default = kotlin.math.PI)

    override fun process() {}
}