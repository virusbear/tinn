package com.virusbear.tinn.nodes

import com.virusbear.tinn.Context
import com.virusbear.tinn.registry.Register

class ConsoleDebugNode: BaseNode("Console Debug", ConsoleDebugNode) {
    @Register("tinn:console-debug")
    companion object: NodeIdentifier("Console", NodeCategory.System, factory = { ConsoleDebugNode() })

    val input: Any? by input("Input")

    override fun process(context: Context) {
        input?.let {
            println(it)
        }
    }
}