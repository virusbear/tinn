package com.virusbear.tinn.nodes.math

import com.virusbear.tinn.Context
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier

class ModuloNode: BaseNode("Modulo", ModuloNode) {
    companion object: NodeIdentifier("Modulo", NodeCategory.Math, factory = { ModuloNode() })

    val input: Double by input("Input", default = 0.0)
    val module: Double by input("Module", default = 1.0)
    var result: Double by output("Result")

    override fun process(context: Context) {
        result = input % module
    }
}