package com.virusbear.tinn.nodes.primitive

import com.virusbear.tinn.Context
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier

class TextNode: BaseNode("Text", TextNode) {
    companion object: NodeIdentifier("Text", NodeCategory.Utility, factory = { TextNode() })

    val input: String by input("Input", default = "")
    var output: String by output("Output")

    override fun process(context: Context) {
        output = input
    }
}