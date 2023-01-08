package com.virusbear.tinn.nodes

import com.virusbear.tinn.*
import com.virusbear.tinn.events.NodeAddedEvent
import com.virusbear.tinn.events.NodeEnteredEvent

class ProgramNode(val program: Program): PortSynchronizingNode("Program", identifier = ProgramNode, deletable = false, dynamicInputsAllowed = true, dynamicOutputsAllowed = false) {
    @Register
    companion object: NodeIdentifier("Program", NodeCategory.System, internal = true, factory = { ctx ->
        val program = ctx[ProgramContextElement]?.program
        require(program != null) { "Creating Program node without Program in context is prohibited" }

        ProgramNode(program)
    })

    private var globalStateNode: PortSynchronizingNode = PortSynchronizingNode("Globals", deletable = false, dynamicInputsAllowed = false, dynamicOutputsAllowed = true)
    private var lastStateNode: PortSynchronizingNode = PortSynchronizingNode("Last State", deletable = false, dynamicInputsAllowed = false, dynamicOutputsAllowed = true)
    private var nextStateNode: PortSynchronizingNode = PortSynchronizingNode("Next State", deletable = false, dynamicInputsAllowed = true, dynamicOutputsAllowed = false)

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

    override fun process(context: Context) {
        propagate()
        nextStateNode.propagate()

        println("Program Name ${context[ProgramContextElement]?.program?.name}")
    }

    override fun save(writer: SceneWriter) {
        super.save(writer)

        writer.write("globalStateNodeId", globalStateNode.id)
        writer.write("lastStateNodeId", lastStateNode.id)
        writer.write("nextStateNodeId", nextStateNode.id)
    }

    override fun load(reader: SceneReader, context: Context) {
        super.load(reader, context)

        program.nodespace.nodeByIdOrNull(reader.int("globalStateNodeId"))?.let {
            if(it != globalStateNode) {
                program.nodespace -= globalStateNode
                program.nodespace += it
            }

            globalStateNode = it as PortSynchronizingNode
        }
        program.nodespace.nodeByIdOrNull(reader.int("lastStateNodeId"))?.let {
            if(it != lastStateNode) {
                program.nodespace -= lastStateNode
                program.nodespace += it
            }

            lastStateNode = it as PortSynchronizingNode
        }
        program.nodespace.nodeByIdOrNull(reader.int("nextStateNodeId"))?.let {
            if(it != nextStateNode) {
                program.nodespace -= nextStateNode
                program.nodespace += it
            }

            nextStateNode = it as PortSynchronizingNode
        }
    }
}