package com.virusbear.tinn.nodes

import com.virusbear.tinn.Context
import kotlin.reflect.KClass

open class PortSynchronizingNode(
    name: String,
    identifier: NodeIdentifier? = null,
    deletable: Boolean = true,
    dynamicInputsAllowed: Boolean = true,
    dynamicOutputsAllowed: Boolean = true
): DynamicPortNode(
    name,
    identifier ?: NodeIdentifier(name, NodeCategory.System, internal = true, factory = { PortSynchronizingNode(name, null, deletable, dynamicInputsAllowed, dynamicOutputsAllowed) }),
    deletable,
    dynamicInputsAllowed,
    dynamicOutputsAllowed
) {

    private var syncInputNode: DynamicPortNode? = null
    private var syncOutputNode: DynamicPortNode? = null

    private val inputPropagationMap = HashMap<Port, Port>()
    private val outputPropagationMap = HashMap<Port, Port>()

    fun synchronizeInputsWith(node: DynamicPortNode) {
        syncInputNode = node
    }

    fun synchronizeOutputsWith(node: DynamicPortNode) {
        syncOutputNode = node
    }

    override fun <T> addPort(direction: PortDirection, name: String, type: KClass<*>, default: T?): Port {
        return ports.firstOrNull { it.direction == direction && it.name == name && it.type == type && it.default == default } ?:
            super.addPort(direction, name, type, default).also { port ->
                when(direction) {
                    PortDirection.Input ->  {
                        syncInputNode?.addPort(PortDirection.Output, name, type, default)?.let {
                            inputPropagationMap[port] = it
                        }
                    }
                    PortDirection.Output ->  {
                        syncOutputNode?.addPort(PortDirection.Input, name, type, default)?.let {
                            outputPropagationMap[port] = it
                        }
                    }
                }
            }
    }

    override fun removePort(port: Port) {
        if(port !in ports) return

        super.removePort(port)

        inputPropagationMap[port]?.let {
            if(it.node is DynamicPortNode) {
                (it.node as DynamicPortNode).removePort(it)
            }
        }

        outputPropagationMap[port]?.let {
            if(it.node is DynamicPortNode) {
                (it.node as DynamicPortNode).removePort(it)
            }
        }
    }

    fun propagate() {
        inputPropagationMap.forEach { (source, target) ->
            target.value = source.value
        }
    }

    override fun process(context: Context) {}
}