package com.virusbear.tinn.nodes.math

import com.virusbear.tinn.nodes.*
import com.virusbear.tinn.Context

class DivideNode: BaseNode("Divide", DivideNode) {
    companion object: NodeIdentifier("Divide", NodeCategory.Math, factory = { DivideNode() })

    val a: Double by input("A", default = 0.0)
    val b: Double by input("B", default = 0.0)
    var result: Double by output("Result")

    override fun process(context: Context) {
        result = a / b
    }
}