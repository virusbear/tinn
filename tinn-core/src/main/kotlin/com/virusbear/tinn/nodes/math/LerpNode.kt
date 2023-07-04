package com.virusbear.tinn.nodes.math

import com.virusbear.tinn.nodes.*
import com.virusbear.tinn.Context
import com.virusbear.tinn.registry.Register

class LerpNode: BaseNode("Lerp", LerpNode) {
    @Register("tinn:lerp")
    companion object: NodeIdentifier("Lerp", NodeCategory.Math, factory = { LerpNode() })

    val start: Double by input("Start", 0.0)
    val end: Double by input("End", 0.0)
    val t: Double by input("t", 0.0)
    var output: Double by output("Output", 0.0)

    override fun process(context: Context) {
        output = (start * (1.0 - t)) + (end * t)
    }
}