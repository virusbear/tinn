package com.virusbear.tinn.nodes

import com.virusbear.tinn.ProcessingContext

class ConsoleDebugNode: BaseNode("Console Debug", ConsoleDebugNode) {
    companion object: NodeIdentifier("Console", NodeCategory.System, factory = { ConsoleDebugNode() })

    val input: Any? by input("Input")

    override fun process(context: ProcessingContext) {
        input?.let {
            println(it)
        }
    }
}