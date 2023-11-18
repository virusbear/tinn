package com.virusbear.tinn.opengl

import com.virusbear.tinn.GraphicsContext
import com.virusbear.tinn.Driver
import com.virusbear.tinn.RenderThread
import com.virusbear.tinn.Trackable
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFW.glfwMakeContextCurrent
import org.lwjgl.system.MemoryUtil.NULL
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class GraphicsContextGL(
    override val driver: Driver
): GraphicsContext {
    override val renderThread: RenderThread = RenderThreadGL()

    var native: Long = NULL
        private set

    fun makeContextCurrent(context: Long) {
        native = context

        renderThread.schedule {
            glfwMakeContextCurrent(native)
        }
    }
}