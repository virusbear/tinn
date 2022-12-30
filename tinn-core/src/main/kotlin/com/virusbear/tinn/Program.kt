package com.virusbear.tinn

import com.virusbear.tinn.nodes.Nodespace
import com.virusbear.tinn.nodes.ProgramNode
import kotlin.time.Duration

class Program private constructor(): BaseDestroyable() {
    //TODO: how to make this more nice
    constructor(
        name: String = "Program",
        programNode: ProgramNode,
        initializationNodespace: Nodespace,
        nodespace: Nodespace
    ): this() {
        this.programNode = programNode
        this.initializationNodespace = initializationNodespace
        this.nodespace = nodespace
        this.name = name
    }
    constructor(name: String = "Program") : this() {
        programNode = ProgramNode(this)
        initializationNodespace = Nodespace("Init $name")
        nodespace = Nodespace(name)
        this.name = name
    }

    lateinit var nodespace: Nodespace
        private set
    lateinit var initializationNodespace: Nodespace
        private set
    lateinit var programNode: ProgramNode
        private set
    var name: String = "Program"
        private set

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
    }

    //TODO: Program.makeCurrent?

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
        initializationNodespace.evaluate()
    }

    //TODO: how to handle time?
    //TODO: fps limit?
    fun update() {
        if(destroyed) return

        if(running || steps > 0) {
            if(steps > 0) steps--
            nodespace.evaluate()
        }
    }
    fun save(writer: SceneWriter) {
        writer.write("version", SCENE_VERSION)
        writer.write("name", name)
        writer.write("programNodeId", programNode.id)
        writer.writeCompound("init", initializationNodespace) { it.save(this) }
        writer.writeCompound("content", nodespace) { it.save(this) }
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
        fun load(reader: SceneReader): Program {
            val version = reader.string("version")
            require(SCENE_VERSION.version >= version.version) { "Unsupported file version. Unable to load Program" }

            val name = reader.string("name")
            val programNodeId = reader.int("programNodeId")
            val initNodespace = reader.compound("init") { Nodespace.load(this) }
            val contentNodespace = reader.compound("content") { Nodespace.load(this) }

            val programNode = (initNodespace.nodeByIdOrNull(programNodeId) as? ProgramNode?) ?: error("No program node found in scene")

            return Program(name, programNode, initNodespace, contentNodespace)
        }
    }
}