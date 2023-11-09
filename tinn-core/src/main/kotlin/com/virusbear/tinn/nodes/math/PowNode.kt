package com.virusbear.tinn.nodes.math

import com.virusbear.tinn.ProcessingContext
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import kotlin.math.pow

class PowNode: BaseNode("Pow", PowNode) {
    companion object: NodeIdentifier("Pow", NodeCategory.Math, factory = { PowNode() })

    val base: Double by input("Base", 0.0)
    val exponent: Double by input("Exponent", 1.0)
    var output: Double by output("Output", 0.0)

    override fun process(context: ProcessingContext) {
        output = base.pow(exponent)
    }
}