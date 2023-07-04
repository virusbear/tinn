package com.virusbear.tinn.nodes.math

import com.virusbear.tinn.Context
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier

class AddNode: BaseNode("Add", AddNode) {
    companion object: NodeIdentifier("Add", NodeCategory.Math, factory = { AddNode() })

    val a: Double by input("A", default = 0.0)
    val b: Double by input("B", default = 0.0)
    var result: Double by output("Result")

    override fun process(context: Context) {
        result = a + b
    }
}