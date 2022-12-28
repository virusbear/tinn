package com.virusbear.tinn

import com.virusbear.tinn.nodes.Nodespace
import com.virusbear.tinn.nodes.ProgramNode
import kotlin.time.Duration

//TODO: run nodeeditor only once initially to create program instance.
//TODO: run content nodespace for each frame.
//TODO: pass in any initially created state 
class Program(val name: String = "Program"): BaseDestroyable() {
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

    override fun destroy() {
        super.destroy()

        nodespace.destroy()
        initializationNodespace.destroy()
        frame = 0
        time = Duration.ZERO
        running = false
    }
}

