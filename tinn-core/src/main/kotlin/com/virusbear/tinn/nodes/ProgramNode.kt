package com.virusbear.tinn.nodes

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.Program
import com.virusbear.tinn.math.IVec2
import kotlin.time.Duration

class ProgramNode: BaseNode("Program") {
    @Register
    companion object: NodeIdentifier("Program", NodeCategory.System, ::ProgramNode)

    var time: Duration by output("Time", default = Duration.ZERO)
    var frame: Long by output("Frame", default = 0L)
    var size: IVec2 by output("Size", default = IVec2.ZERO)
    var lastFrame: ColorBuffer? by output("Last Frame", default = null)

    override fun process() {
        val program = Program.current

        time = program.time
        frame = program.frame
        //size = IVec2(program.target.width, program.target.height)
        //lastFrame = program.target.colorbuffer(0)
    }
}