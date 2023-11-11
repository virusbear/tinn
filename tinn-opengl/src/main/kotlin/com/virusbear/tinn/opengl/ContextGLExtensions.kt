package com.virusbear.tinn.opengl

import com.virusbear.tinn.Monitor
import com.virusbear.tinn.color.Color
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.Vec2
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL30C
import org.lwjgl.opengl.GLCapabilities
import org.lwjgl.system.MemoryStack

fun ContextGL.glfwDefaultWindowHints() =
    execute {
        GLFW.glfwDefaultWindowHints()
    }

fun ContextGL.glfwWindowHint(hint: Int, value: Int) =
    execute {
        GLFW.glfwWindowHint(hint, value)
    }

fun ContextGL.glfwCreateWindow(width: Int, height: Int, title: String, monitor: Long, share: Long): Long =
    execute {
        GLFW.glfwCreateWindow(width, height, title, monitor, share)
    }

fun ContextGL.glfwGetWindowSize(window: Long): IVec2 =
    execute {
        MemoryStack.stackPush().use { stack ->
            val pWidth = stack.mallocInt(1)
            val pHeight = stack.mallocInt(1)

            GLFW.glfwGetWindowSize(window, pWidth, pHeight)

            IVec2(pWidth.get(), pHeight.get())
        }
    }

fun ContextGL.glfwSetWindowPos(window: Long, pos: IVec2) =
    execute {
        GLFW.glfwSetWindowPos(window, pos.x, pos.y)
    }

fun ContextGL.glfwSwapInterval(interval: Int) =
    execute {
        GLFW.glfwSwapInterval(interval)
    }

fun ContextGL.createCapabilities(): GLCapabilities {
    return execute {
        GL.createCapabilities()
    }
}

fun ContextGL.glClearColor(color: Color) {
    execute {
        GL30C.glClearColor(color.r.toFloat(), color.g.toFloat(), color.b.toFloat(), color.a.toFloat())
    }
}

fun ContextGL.glfwWindowShouldClose(window: Long): Boolean =
    execute {
        GLFW.glfwWindowShouldClose(window)
    }

fun ContextGL.glClear(mask: Int) =
    execute {
        GL30C.glClear(mask)
    }

fun ContextGL.glfwSwapBuffers(window: Long) =
    execute {
        GLFW.glfwSwapBuffers(window)
    }

fun ContextGL.glfwPollEvents() =
    execute {
        GLFW.glfwPollEvents()
    }

fun ContextGL.glfwDestroyWindow(window: Long) =
    execute {
        GLFW.glfwDestroyWindow(window)
    }

fun ContextGL.glfwGetWindowContentScale(window: Long): Vec2 =
    execute {
        MemoryStack.stackPush().use { stack ->
            val scaleX = stack.mallocFloat(1)
            val scaleY = stack.mallocFloat(1)

            GLFW.glfwGetWindowContentScale(window, scaleX, scaleY)

            Vec2(scaleX.get().toDouble(), scaleY.get().toDouble())
        }
    }

fun ContextGL.glfwGetWindowMonitor(window: Long): Long =
    execute {
        GLFW.glfwGetWindowMonitor(window)
    }

fun ContextGL.glfwGetWindowPos(window: Long): IVec2 =
    execute {
        MemoryStack.stackPush().use { stack ->
            val xPos = stack.mallocInt(1)
            val yPos = stack.mallocInt(1)

            GLFW.glfwGetWindowPos(window, xPos, yPos)

            IVec2(xPos.get(), yPos.get())
        }
    }

fun ContextGL.glfwSetWindowSizeCallback(window: Long, callback: (size: IVec2) -> Unit) =
    execute {
        GLFW.glfwSetWindowSizeCallback(window) { callbackWindow, width, height ->
            if(callbackWindow == window) {
                callback(IVec2(width, height))
            }
        }
    }
