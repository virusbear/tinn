package com.virusbear.tinn.nodes

class ConsoleDebugNode: BaseNode("Console Debug", ConsoleDebugNode) {
    @Register
    companion object: NodeIdentifier("Console", NodeCategory.System, { ConsoleDebugNode() })

    val input: Any? by input("Input")

    override fun process() {
        input?.let {
            println(it)
        }
    }
}