package com.virusbear.tinn.nodes

import com.virusbear.tinn.EventBus
import com.virusbear.tinn.events.NodeEnteredEvent
import com.virusbear.tinn.events.NodeRemovedEvent

class NodespaceNode: BaseNode("Nodespace", NodespaceNode) {
    @Register
    companion object: NodeIdentifier("Nodespace", NodeCategory.Utility, { NodespaceNode() })

    val output: Nodespace by output("Nodespace", Nodespace(name))

    init {
        EventBus.subscribe<NodeEnteredEvent> {
            if(it.node == this) {
                output.makeCurrent()
            }
        }

        EventBus.subscribe<NodeRemovedEvent> {
            if(it.node == this) {
                output.destroy()
            }
        }
    }

    override fun process() { }
}