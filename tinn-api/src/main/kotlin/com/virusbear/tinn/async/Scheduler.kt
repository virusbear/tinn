package com.virusbear.tinn.async

import com.virusbear.tinn.Destroyable

interface Scheduler: Destroyable {
    fun <T> schedule(task: () -> T): Task<T>

    fun <T> execute(block: () -> T): T =
        schedule(block).await()

    fun <T> submit(block: () -> T) {
        schedule(block)
    }
}