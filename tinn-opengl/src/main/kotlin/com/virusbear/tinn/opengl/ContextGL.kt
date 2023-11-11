package com.virusbear.tinn.opengl

import com.virusbear.tinn.Driver
import com.virusbear.tinn.Trackable
import org.lwjgl.glfw.GLFW
import org.lwjgl.system.MemoryUtil.NULL
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ContextGL(driver: Driver): Trackable(driver) {
    private var native: Long = NULL
    private val owner: ExecutorService = Executors.newSingleThreadExecutor()

    fun makeCurrent(context: Long) {
        ensureAlive()

        native = context
        glfwMakeContextCurrent()
    }

    fun clearCurrent() {
        ensureAlive()

        native = NULL
        glfwMakeContextCurrent()
    }

    fun <T> execute(block: () -> T): T {
        ensureAlive()
        return owner.submit(block).get()
    }


    fun <T> future(block: () -> T): CompletableFuture<T> {
        ensureAlive()
        val future = CompletableFuture<T>()

        owner.submit {
            future.complete(block())
        }

        return future
    }

    fun schedule(block: () -> Unit) {
        ensureAlive()
        owner.submit(block)
    }

    override fun destroy() {
        super.destroy()

        owner.shutdown()
    }

    private fun glfwMakeContextCurrent() {
        schedule {
            GLFW.glfwMakeContextCurrent(native)
        }
    }

    private fun ensureAlive() {
        require(!destroyed) { "Trying to run in destroyed Context" }
    }
}