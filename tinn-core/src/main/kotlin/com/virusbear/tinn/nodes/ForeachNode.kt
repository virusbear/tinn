package com.virusbear.tinn.nodes

class ForeachNode: GroupNode("Foreach", ForeachNode) {
    @Register
    companion object: NodeIdentifier("Foreach", NodeCategory.Utility, { ForeachNode() })

    val list: List<Any> by input("Input", emptyList())
    var result: List<Any?> by output("Output", emptyList())

    val elementInputPort: Port = inputNode.addPort(PortDirection.Output, "Element", Any::class, null)
    val elementOutputPort: Port = outputNode.addPort(PortDirection.Input, "Element", Any::class, null)

    override fun process() {
        propagate()

        result = list.map {
            elementInputPort.value = it
            contentNodespace.evaluate()
            elementOutputPort.value
        }

        outputNode.propagate()
    }
}