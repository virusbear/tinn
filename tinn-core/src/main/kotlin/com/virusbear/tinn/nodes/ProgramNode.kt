package com.virusbear.tinn.nodes

import com.virusbear.tinn.EventBus
import com.virusbear.tinn.Program
import com.virusbear.tinn.events.ProgramActivateEvent
import kotlin.time.Duration

class ProgramNode: BaseNode("Program") {
    @Register
    companion object: NodeIdentifier("Program", NodeCategory.System, ::ProgramNode)

    init {
        EventBus.subscribe<ProgramActivateEvent> {
            program = it.program
        }
    }

    private var program: Program? = null

    var time: Duration by output("Time", default = Duration.ZERO)
    var frame: Long by output("Frame", default = 0L)

    override fun process() {
        val prog = program ?: return

        time = prog.time
        frame = prog.frame
    }
}