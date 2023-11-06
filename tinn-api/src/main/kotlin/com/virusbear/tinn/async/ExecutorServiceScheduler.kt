package com.virusbear.tinn.async

import com.virusbear.tinn.BaseDestroyable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

class ExecutorServiceScheduler(
    private val executor: ExecutorService
): Scheduler, BaseDestroyable() {
    override fun <T> schedule(task: () -> T): Task<T> =
        executor.submit(task).asFutureTask()

    private fun <T> Future<T>.asFutureTask(): Task<T> =
        FutureTask(this)

    private class FutureTask<out T>(
        private val future: Future<T>
    ): Task<T> {
        override fun await(): T =
            future.get()
    }
}