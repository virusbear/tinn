package com.virusbear.tinn

import com.virusbear.tinn.events.ProgramActivateEvent
import com.virusbear.tinn.nodes.Nodespace
import kotlin.time.Duration

class Program: BaseDestroyable() {
    val nodespace: Nodespace = Nodespace()

    //val target: RenderTarget = Driver.driver.createRenderTarget(0, 0)
    var frame: Long = 0
        private set
    var time: Duration = Duration.ZERO
        private set
    var running: Boolean = false
        private set

    fun makeCurrent() {
        EventBus.publish(ProgramActivateEvent(this))
        nodespace.makeCurrent()
    }

    fun step() {
        if(destroyed) return

        nodespace.evaluate()
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
    }

    //TODO: how to handle time?
    //TODO: fps limit?
    fun update() {
        if(destroyed) return

        if(running) {
            step()
        }
    }

    override fun destroy() {
        super.destroy()

        nodespace.destroy()
        frame = 0
        time = Duration.ZERO
        running = false
    }
}

