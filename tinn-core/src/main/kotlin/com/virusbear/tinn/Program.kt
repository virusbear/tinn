package com.virusbear.tinn

import com.virusbear.tinn.events.NodeEnteredEvent
import com.virusbear.tinn.events.NodespaceActivateEvent
import com.virusbear.tinn.nodes.*
import kotlin.time.Duration

class Program(name: String = "Program"): BaseDestroyable(), SceneSavable {
    var name: String = name
        private set
    val nodespace: Nodespace = Nodespace(name)
    val initializationNodespace: Nodespace = Nodespace("Init $name")
    var programNode = ProgramNode(this)

    var frame: Long = 0
        private set
    var time: Duration = Duration.ZERO
        private set
    var running: Boolean = false
        private set

    private var steps: Int = 0

    init {
        initializationNodespace += programNode
        initializationNodespace.makeCurrent()

        EventBus.subscribe<NodeEnteredEvent> {
            if(it.node.nodespace == initializationNodespace) {
                nodespace.makeCurrent()
            }
        }
    }

    fun step() {
        if(destroyed) return

        steps++
    }

    fun start() {
        if(destroyed) return

        running = true
    }
    fun stop() {
        if(destroyed) return

        running = false
    }
    fun reset() {
        if(destroyed) return

        frame = 0
        initializationNodespace.evaluate(ProgramContextElement(this))
    }

    //TODO: how to handle time?
    //TODO: fps limit?
    fun update() {
        if(destroyed) return

        if(running || steps > 0) {
            if(steps > 0) steps--

            nodespace.evaluate(ProgramContextElement(this))
        }
    }

    override fun save(writer: SceneWriter) {
        writer.write("version", SCENE_VERSION)
        writer.write("name", name)
        writer.writeCompound("init", initializationNodespace) { it.save(this) }
        writer.writeCompound("content", nodespace) { it.save(this) }
        writer.write("programNodeId", programNode.id)
    }

    override fun load(reader: SceneReader, context: Context) {
        val version = reader.string("version")
        require(SCENE_VERSION.version >= version.version) {  "Unsupported file version. Unable to load Program" }

        name = reader.string("name")

        val newContext = context + ProgramContextElement(this)

        reader.compound("content") {
            nodespace.load(this, newContext)
        }

        reader.compound("init") {
            initializationNodespace.load(this, newContext)
        }

        initializationNodespace.nodeByIdOrNull(reader.int("programNodeId"))?.let {
            programNode = it as ProgramNode
        }

        EventBus.publish(NodespaceActivateEvent(initializationNodespace))
    }

    override fun destroy() {
        super.destroy()

        nodespace.destroy()
        initializationNodespace.destroy()
        frame = 0
        time = Duration.ZERO
        running = false
    }

    companion object {
        private const val SCENE_VERSION = "0.0.1"
    }
}

class ProgramContextElement(
    val program: Program
): AbstractContextElement(ProgramContextElement) {
    companion object Key: Context.Key<ProgramContextElement>

    override fun toString(): String =
        "Program(${program.name})"
}

