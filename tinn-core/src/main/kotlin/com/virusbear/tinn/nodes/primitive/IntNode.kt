package com.virusbear.tinn.nodes.primitive

import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.Register

class IntNode: BaseNode("Int", IntNode) {
    @Register
    companion object: NodeIdentifier("Int", NodeCategory.Math, { IntNode() })

    val input: Int by input("Input", default = 0)
    var output: Int by output("Output")

    override fun process() {
        output = input
    }
}