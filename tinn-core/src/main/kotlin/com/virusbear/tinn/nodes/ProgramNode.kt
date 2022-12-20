package com.virusbear.tinn.nodes

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.EventBus
import com.virusbear.tinn.Program
import com.virusbear.tinn.events.ProgramActivateEvent
import com.virusbear.tinn.math.IVec2
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
    var size: IVec2 by output("Size", default = IVec2.ZERO)
    var lastFrame: ColorBuffer? by output("Last Frame", default = null)

    override fun process() {
        val prog = program ?: return

        time = prog.time
        frame = prog.frame
        //size = IVec2(program.target.width, program.target.height)
        //lastFrame = program.target.colorbuffer(0)
    }
}