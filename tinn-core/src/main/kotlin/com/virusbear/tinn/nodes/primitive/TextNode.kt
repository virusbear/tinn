package com.virusbear.tinn.nodes.primitive

import com.virusbear.tinn.nodes.*
import com.virusbear.tinn.Context

class TextNode: BaseNode("Text", TextNode) {
    @Register
    companion object: NodeIdentifier("Text", NodeCategory.Utility, factory = { TextNode() })

    val input: String by input("Input", default = "")
    var output: String by output("Output")

    override fun process(context: Context) {
        output = input
    }
}