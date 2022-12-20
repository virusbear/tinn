package com.virusbear.tinn.nodes.math

import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.Register

class ModuloNode: BaseNode("Modulo") {
    @Register
    companion object: NodeIdentifier("Modulo", NodeCategory.Math, ::ModuloNode)

    val input: Double by input("Input", default = 0.0)
    val module: Double by input("Module", default = 1.0)
    var result: Double by output("Result")

    override fun process() {
        result = input % module
    }
}