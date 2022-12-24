package com.virusbear.tinn.nodes.convert

import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.Register

class FloatToIntNode: BaseNode("Float to Int") {
    @Register
    companion object: NodeIdentifier("Float to Int", NodeCategory.Convert, ::FloatToIntNode)

    val input: Double by input("Float", 0.0)
    var output: Int by output("Int", 0)

    override fun process() {
        output = input.toInt()
    }
}