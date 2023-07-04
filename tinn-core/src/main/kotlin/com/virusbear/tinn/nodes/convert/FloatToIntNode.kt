package com.virusbear.tinn.nodes.convert

import com.virusbear.tinn.nodes.*
import com.virusbear.tinn.Context
import com.virusbear.tinn.registry.Register

class FloatToIntNode: BaseNode("Float to Int", FloatToIntNode) {
    @Register("tinn:float2int")
    companion object: NodeIdentifier("Float to Int", NodeCategory.Convert, factory = { FloatToIntNode() })

    val input: Double by input("Float", 0.0)
    var output: Int by output("Int", 0)

    override fun process(context: Context) {
        output = input.toInt()
    }
}