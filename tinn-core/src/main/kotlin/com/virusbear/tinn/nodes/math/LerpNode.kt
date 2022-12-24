package com.virusbear.tinn.nodes.math

import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.Register

class LerpNode: BaseNode("Lerp") {
    @Register
    companion object: NodeIdentifier("Lerp", NodeCategory.Math, ::LerpNode)

    val start: Double by input("Start", 0.0)
    val end: Double by input("End", 0.0)
    val t: Double by input("t", 0.0)
    var output: Double by output("Output", 0.0)

    override fun process() {
        output = (start * (1.0 - t)) + (end * t)
    }
}