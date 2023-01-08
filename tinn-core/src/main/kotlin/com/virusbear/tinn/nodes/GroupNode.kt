package com.virusbear.tinn.nodes

import com.virusbear.tinn.EventBus
import com.virusbear.tinn.events.NodeEnteredEvent
import com.virusbear.tinn.Context

open class GroupNode(name: String = "Group", identifier: NodeIdentifier): PortSynchronizingNode(name, identifier) {

    @Register
    companion object: NodeIdentifier("Group", NodeCategory.Utility, factory = { GroupNode(identifier = it[NodeIdentifier]!!) })

    protected val contentNodespace: Nodespace = Nodespace(name)
    protected val inputNode = PortSynchronizingNode("Input", deletable = false, dynamicInputsAllowed = false, dynamicOutputsAllowed = true).also { contentNodespace += it }
    protected val outputNode = PortSynchronizingNode("Output", deletable = false, dynamicInputsAllowed = true, dynamicOutputsAllowed = false).also { contentNodespace += it }

    init {
        synchronizeInputsWith(inputNode)
        synchronizeOutputsWith(outputNode)
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

    override fun process(context: Context) {
        propagate()

        contentNodespace.evaluate(context)

        outputNode.propagate()
    }
}