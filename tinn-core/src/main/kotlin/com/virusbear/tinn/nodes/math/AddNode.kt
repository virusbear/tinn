package com.virusbear.tinn.nodes.math

import com.virusbear.tinn.nodes.*

class AddNode: BaseNode("Add", AddNode) {
    @Register
    companion object: NodeIdentifier("Add", NodeCategory.Math, { AddNode() })

    val a: Double by input("A", default = 0.0)
    val b: Double by input("B", default = 0.0)
    var result: Double by output("Result")

    override fun process() {
        result = a + b
    }
}