package com.virusbear.tinn.nodes.convert

import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.Register

class IntToFloatNode: BaseNode("Int to Float") {
    @Register
    companion object: NodeIdentifier("Int to Float", NodeCategory.Convert, ::IntToFloatNode)

    val input: Int by input("Int", 0)
    var output: Double by output("Float", 0.0)

    override fun process() {
        output = input.toDouble()
    }
}