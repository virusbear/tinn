package com.virusbear.tinn.nodes

import com.virusbear.tinn.Context
import com.virusbear.tinn.EventBus
import com.virusbear.tinn.SceneWriter
import com.virusbear.tinn.events.NodeEnteredEvent
import com.virusbear.tinn.events.NodeRemovedEvent
import com.virusbear.tinn.registry.Register

class NodespaceNode: BaseNode("Nodespace", NodespaceNode) {
    @Register("tinn:nodespace")
    companion object: NodeIdentifier("Nodespace", NodeCategory.Utility, factory = { NodespaceNode() })

    var output: Nodespace? by output("Nodespace", default = null)

    init {
        EventBus.subscribe<NodeEnteredEvent> {
            if(it.node == this) {
                output?.makeCurrent()
            }
        }

        EventBus.subscribe<NodeRemovedEvent> {
            if(it.node == this) {
                output?.destroy()
            }
        }
    }

    override fun onAttach(nodespace: Nodespace) {
        output = Nodespace(name, nodespace)
        super.onAttach(nodespace)
    }

    override fun onDetach(nodespace: Nodespace) {
        output?.destroy()
        output = null
        super.onDetach(nodespace)
    }

    override fun process(context: Context) { }

    override fun save(writer: SceneWriter) {
        super.save(writer)

        output?.let {
            writer.writeCompound("nodespace", it) {
                it.save(this)
            }
        }
    }
}