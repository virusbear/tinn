package com.virusbear.tinn.events

import com.virusbear.tinn.Event

sealed class ProgramControlEvent: Event {
    object Step: ProgramControlEvent()
    object Start: ProgramControlEvent()
    object Stop: ProgramControlEvent()
    object Reset: ProgramControlEvent()
    object Pause: ProgramControlEvent()
}