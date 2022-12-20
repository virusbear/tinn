package com.virusbear.tinn.events

import com.virusbear.tinn.Event
import com.virusbear.tinn.Program

data class ProgramActivateEvent(
    val program: Program
): Event