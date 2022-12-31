package com.virusbear.tinn

import com.virusbear.tinn.nodes.Nodespace
import com.virusbear.tinn.nodes.ProgramNode
import kotlin.time.Duration

class Program(val name: String = "Program"): BaseDestroyable(), SceneSavable {
    val nodespace: Nodespace = Nodespace(name)
    val initializationNodespace: Nodespace = Nodespace("Init $name")
    val programNode = ProgramNode(this)

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

    override fun save(writer: SceneWriter) {
        writer.write("version", SCENE_VERSION)
        writer.write("name", name)
        writer.writeCompound("init", initializationNodespace) { it.save(this) }
        writer.writeCompound("content", nodespace) { it.save(this) }
        writer.writeCompound("program", programNode) { it.save(this) }
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

