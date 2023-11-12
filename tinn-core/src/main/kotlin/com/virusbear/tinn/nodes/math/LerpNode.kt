package com.virusbear.tinn.nodes.math

import com.virusbear.tinn.ProcessingContext
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier

class LerpNode: BaseNode("Lerp", LerpNode) {
    companion object: NodeIdentifier("Lerp", NodeCategory.Math, factory = { LerpNode() })

    val start: Double by input("Start", 0.0)
    val end: Double by input("End", 0.0)
    val t: Double by input("t", 0.0)
    var output: Double by output("Output", 0.0)

    override fun process(context: ProcessingContext) {
        output = (start * (1.0 - t)) + (end * t)
    }
}