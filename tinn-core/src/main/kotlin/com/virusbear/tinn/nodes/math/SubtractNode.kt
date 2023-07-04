package com.virusbear.tinn.nodes.math

import com.virusbear.tinn.nodes.*
import com.virusbear.tinn.Context
import com.virusbear.tinn.registry.Register

class SubtractNode: BaseNode("Subtract", SubtractNode) {
    @Register("tinn:subtract")
    companion object: NodeIdentifier("Subtract", NodeCategory.Math, factory = { SubtractNode() })

    val a: Double by input("A", default = 0.0)
    val b: Double by input("B", default = 0.0)
    var result: Double by output("Result")

    override fun process(context: Context) {
        result = a - b
    }
}