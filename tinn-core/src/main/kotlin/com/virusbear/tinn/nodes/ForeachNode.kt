package com.virusbear.tinn.nodes

import com.virusbear.tinn.ProcessingContext

class ForeachNode: GroupNode("Foreach", ForeachNode) {
    companion object: NodeIdentifier("Foreach", NodeCategory.Utility, factory = { ForeachNode() })

    val list: List<Any> by input("Input", emptyList())
    var result: List<Any?> by output("Output", emptyList())

    val elementInputPort: Port = inputNode.addPort(PortDirection.Output, "Element", Any::class, null)
    val elementOutputPort: Port = outputNode.addPort(PortDirection.Input, "Element", Any::class, null)

    override fun process(context: ProcessingContext) {
        propagate()

        result = list.map {
            elementInputPort.value = it
            contentNodespace.evaluate(context)
            elementOutputPort.value
        }

        outputNode.propagate()
    }
}