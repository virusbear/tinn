package com.virusbear.tinn.async

import com.virusbear.tinn.Trackable

class TrackableScheduler(private val scheduler: Scheduler): Scheduler, Trackable() {
    override fun <T> schedule(task: () -> T): Task<T> =
        scheduler.schedule(task)

    override fun destroy() {
        super.destroy()

        scheduler.destroy()
    }
}

fun Scheduler.trackable(): TrackableScheduler =
    if(this is TrackableScheduler) {
        this
    } else {
        TrackableScheduler(this)
    }