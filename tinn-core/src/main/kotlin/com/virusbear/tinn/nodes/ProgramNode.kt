package com.virusbear.tinn.nodes

import com.virusbear.tinn.EventBus
import com.virusbear.tinn.Program
import com.virusbear.tinn.events.NodeAddedEvent
import com.virusbear.tinn.events.NodeEnteredEvent
import com.virusbear.tinn.events.ProgramControlEvent

class ProgramNode(private val program: Program): SynchronizingPortNode("Program", NodeIdentifier("", NodeCategory.System, { error("FIXME: This should not happen") }), deletable = false, dynamicInputsAllowed = true, dynamicOutputsAllowed = false) {
    private val globalStateNode = SynchronizingPortNode("Globals", NodeIdentifier("", NodeCategory.System, { error("FIXME: This should not happen") }), deletable = false, dynamicInputsAllowed = false, dynamicOutputsAllowed = true)
    private val lastStateNode = SynchronizingPortNode("Last State", NodeIdentifier("", NodeCategory.System, { error("FIXME: This should not happen") }), deletable = false, dynamicInputsAllowed = false, dynamicOutputsAllowed = true)
    private val nextStateNode = SynchronizingPortNode("Next State", NodeIdentifier("", NodeCategory.System, { error("FIXME: This should not happen") }), deletable = false, dynamicInputsAllowed = true, dynamicOutputsAllowed = false)

    init {
        synchronizeInputsWith(globalStateNode)
        globalStateNode.synchronizeOutputsWith(this)
        lastStateNode.synchronizeOutputsWith(nextStateNode)
        nextStateNode.synchronizeInputsWith(lastStateNode)

        program.nodespace += globalStateNode
        EventBus.publish(NodeAddedEvent(program.nodespace, globalStateNode))

        program.nodespace += lastStateNode
        EventBus.publish(NodeAddedEvent(program.nodespace, lastStateNode))

        program.nodespace += nextStateNode
        EventBus.publish(NodeAddedEvent(program.nodespace, nextStateNode))

        EventBus.subscribe<NodeEnteredEvent> {
            if(it.node == this) {
                program.nodespace.makeCurrent()
            }
        }

    }

    override fun process() {
        propagate()
        nextStateNode.propagate()

        program.update()
    }
}