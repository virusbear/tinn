package com.virusbear.tinn.nodes.primitive

import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.Register

class TextNode: BaseNode("Text") {
    @Register
    companion object: NodeIdentifier("Text", NodeCategory.Utility, ::TextNode)

    val input: String by input("Input", default = "")
    var output: String by output("Output")

    override fun process() {
        output = input
    }
}