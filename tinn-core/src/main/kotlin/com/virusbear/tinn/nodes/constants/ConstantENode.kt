package com.virusbear.tinn.nodes.constants

import com.virusbear.tinn.ProcessingContext
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.Constant
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier

class ConstantENode: BaseNode("E", ConstantENode) {
    companion object: NodeIdentifier("E", NodeCategory.Constant, factory = { ConstantENode() })

    val e: Double by output("E", default = kotlin.math.E)

    override fun process(context: ProcessingContext) {}
}