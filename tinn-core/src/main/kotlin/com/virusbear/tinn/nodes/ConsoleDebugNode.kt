package com.virusbear.tinn.nodes

import com.virusbear.tinn.Context

class ConsoleDebugNode: BaseNode("Console Debug", ConsoleDebugNode) {
    companion object: NodeIdentifier("Console", NodeCategory.System, factory = { ConsoleDebugNode() })

    val input: Any? by input("Input")

    override fun process(context: Context) {
        input?.let {
            println(it)
        }
    }
}