package com.virusbear.tinn.nodes.constants

import com.virusbear.tinn.nodes.*
import com.virusbear.tinn.Context
import com.virusbear.tinn.registry.Register

class ConstantPINode: BaseNode("PI", ConstantPINode) {
    @Register("tinn:constance-pi")
    companion object: NodeIdentifier("PI", NodeCategory.Constant, factory = { ConstantPINode() })

    val pi: Double by output("PI", default = kotlin.math.PI)

    override fun process(context: Context) {}
}