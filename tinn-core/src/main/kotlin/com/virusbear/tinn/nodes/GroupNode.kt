package com.virusbear.tinn.nodes

import com.virusbear.tinn.EventBus
import com.virusbear.tinn.events.NodeEnteredEvent
import kotlin.reflect.KClass

class GroupNode: DynamicPortNode("Group") {
    @Register
    companion object: NodeIdentifier("Group", NodeCategory.Utility, ::GroupNode)

    private val contentNodespace: Nodespace = Nodespace(name)
    private val inputNode = InputNode().also { contentNodespace += it }
    private val outputNode = OutputNode().also { contentNodespace += it }

    private val propagationMatrix = HashMap<Port, Port>()

    override fun <T> addPort(direction: PortDirection, name: String, type: KClass<*>, default: T?): Port {
        val port = super.addPort(direction, name, type, default)

        val contentPort = when(direction) {
            PortDirection.Input -> inputNode.addPort(PortDirection.Output, name, type, default)
            PortDirection.Output -> outputNode.addPort(PortDirection.Input, name, type, default)
        }

        propagationMatrix[port] = contentPort

        return port
    }

    override fun removePort(port: Port) {
        propagationMatrix[port]?.let {
            when(it.direction) {
                PortDirection.Input -> outputNode.removePort(it)
                PortDirection.Output -> inputNode.removePort(it)
            }
        }
        propagationMatrix -= port

        super.removePort(port)
    }

    init {
        EventBus.subscribe<NodeEnteredEvent> {
            if(it.node == this) {
                contentNodespace.makeCurrent()
            }
        }

        /*TODO:
        EventBus.subscribe<NodeRemovedEvent> {
            if(it.node == this) {
                contentNodespace.destroy()
            }
        }*/
    }

    override fun process() {
        ports.filter { it.direction == PortDirection.Input }.forEach { input ->
            propagationMatrix[input]?.let { output ->
                if(output.direction == PortDirection.Output) {
                    output.value = input.value
                }
            }
        }
        contentNodespace.evaluate()

        ports.filter { it.direction == PortDirection.Output }.forEach { output ->
            propagationMatrix[output]?.let { input ->
                if(output.direction == PortDirection.Input) {
                    output.value = input.value
                }
            }
        }
    }

    class InputNode: DynamicPortNode("Input") {
        //TODO: move propagation from GroupNode to this node
        override fun process() {}
    }
    class OutputNode: DynamicPortNode("Output") {
        //TODO: move propagation from GroupNode to this node
        override fun process() {}
    }
}