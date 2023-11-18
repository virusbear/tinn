package com.virusbear.tinn.opengl

import com.virusbear.tinn.RenderThread
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class RenderThreadGL: RenderThread {
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    override fun <T> execute(block: () -> T): T {
        return executor.submit(block).get()
    }

    override fun <T> future(block: () -> T): CompletableFuture<T> {
        val future = CompletableFuture<T>()

        executor.submit {
            future.complete(block())
        }

        return future
    }

    override fun schedule(block: () -> Unit) {
        executor.submit(block)
    }
}