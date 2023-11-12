package com.virusbear.tinn.nodes.constants

import com.virusbear.tinn.ProcessingContext
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.Constant
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier

class ConstantPINode: BaseNode("PI", ConstantPINode) {
    companion object: NodeIdentifier("PI", NodeCategory.Constant, factory = { ConstantPINode() })

    val pi: Double by output("PI", default = kotlin.math.PI)

    override fun process(context: ProcessingContext) {}
}