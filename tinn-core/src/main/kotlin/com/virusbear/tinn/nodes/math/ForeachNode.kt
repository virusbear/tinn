package com.virusbear.tinn.nodes.math

import com.virusbear.tinn.nodes.*

class ForeachNode: GroupNode("Foreach") {
    @Register
    companion object: NodeIdentifier("Foreach", NodeCategory.Utility, ::ForeachNode)

    val list: List<Any> by input("Input", emptyList())
    var result: List<Any?> by output("Output", emptyList())

    val elementInputPort: Port = inputNode.addPort(PortDirection.Output, "Element", Any::class, null)
    val elementOutputPort: Port = outputNode.addPort(PortDirection.Input, "Element", Any::class, null)

    override fun process() {
        propagateInputs()

        result = list.map {
            elementInputPort.value = it
            contentNodespace.evaluate()
            elementOutputPort.value
        }

        propagateOutputs()
    }
}