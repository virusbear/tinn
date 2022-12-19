package com.virusbear.tinn

import com.virusbear.tinn.nodes.Nodespace
import kotlin.time.Duration

class Program {
    companion object {
        var current: Program = Program()
    }

    val nodespace: Nodespace = Nodespace()

    init {
        Nodespace.push(nodespace)
    }

    //val target: RenderTarget = Driver.driver.createRenderTarget(0, 0)
    var frame: Long = 0
    val time: Duration = Duration.ZERO
    var running: Boolean = false

    fun step() {
        nodespace.evaluate()
    }

    fun start() {
        running = true
    }
    fun stop() {
        running = false
    }
    fun reset() {
        frame = 0
    }

    fun update() {
        if(running) {
            step()
        }
    }
}