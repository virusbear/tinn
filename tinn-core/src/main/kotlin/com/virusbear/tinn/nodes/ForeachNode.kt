package com.virusbear.tinn.nodes

import com.virusbear.tinn.Context
import com.virusbear.tinn.registry.Register

class ForeachNode: GroupNode("Foreach", ForeachNode) {
    @Register("tinn:foreach")
    companion object: NodeIdentifier("Foreach", NodeCategory.Utility, factory = { ForeachNode() })

    val list: List<Any> by input("Input", emptyList())
    var result: List<Any?> by output("Output", emptyList())

    val elementInputPort: Port = inputNode.addPort(PortDirection.Output, "Element", Any::class, null)
    val elementOutputPort: Port = outputNode.addPort(PortDirection.Input, "Element", Any::class, null)

    override fun process(context: Context) {
        propagate()

        result = list.map {
            elementInputPort.value = it
            contentNodespace.evaluate(context)
            elementOutputPort.value
        }

        outputNode.propagate()
    }
}