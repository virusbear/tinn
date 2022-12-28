package com.virusbear.tinn.nodes

import com.virusbear.tinn.EventBus
import com.virusbear.tinn.events.NodeEnteredEvent
import kotlin.reflect.KClass

open class GroupNode(name: String = "Group"): SynchronizingPortNode(name) {
    @Register
    companion object: NodeIdentifier("Group", NodeCategory.Utility, ::GroupNode)

    protected val contentNodespace: Nodespace = Nodespace(name)
    protected val inputNode = SynchronizingPortNode("Input", deletable = false, dynamicInputsAllowed = false, dynamicOutputsAllowed = true).also { contentNodespace += it }
    protected val outputNode = SynchronizingPortNode("Output", deletable = false, dynamicInputsAllowed = true, dynamicOutputsAllowed = false).also { contentNodespace += it }

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

    override fun process() {
        propagate()

        contentNodespace.evaluate()

        outputNode.propagate()
    }
}