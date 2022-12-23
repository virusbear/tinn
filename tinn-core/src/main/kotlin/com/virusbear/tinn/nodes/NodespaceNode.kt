package com.virusbear.tinn.nodes

import com.virusbear.tinn.EventBus
import com.virusbear.tinn.events.NodeEnteredEvent

class NodespaceNode: BaseNode("Nodespace") {
    @Register
    companion object: NodeIdentifier("Nodespace", NodeCategory.Utility, ::NodespaceNode)

    val output: Nodespace by output("Nodespace", Nodespace(name))

    init {
        EventBus.subscribe<NodeEnteredEvent> {
            if(it.node == this) {
                output.makeCurrent()
            }
        }

        /*TODO:
        EventBus.subscribe<NodeRemovedEvent> {
            if(it.node == this) {
                contentNodespace.destroy()
            }
        }*/
    }

    override fun process() { }
}