package com.virusbear.tinn.nodes.primitive

import com.virusbear.tinn.Context
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier

class IntNode: BaseNode("Int", IntNode) {
    companion object: NodeIdentifier("Int", NodeCategory.Math, factory = { IntNode() })

    val input: Int by input("Input", default = 0)
    var output: Int by output("Output")

    override fun process(context: Context) {
        output = input
    }
}