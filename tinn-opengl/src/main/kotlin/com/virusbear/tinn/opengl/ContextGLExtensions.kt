package com.virusbear.tinn.opengl

import com.virusbear.tinn.color.Color
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.window.Action
import com.virusbear.tinn.window.Key
import com.virusbear.tinn.window.Mod
import com.virusbear.tinn.window.MouseButton
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWVidMode
import org.lwjgl.opengl.*
import org.lwjgl.system.MemoryStack
import java.nio.ByteBuffer
import java.nio.FloatBuffer
import com.virusbear.tinn.opengl.checkGLErrors as checkGLErrorsTinn

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

fun ContextGL.glfwSetWindowContentScaleCallback(window: Long, callback: (scale: Vec2) -> Unit) =
    execute {
        GLFW.glfwSetWindowContentScaleCallback(window) { callbackWindow, scaleX, scaleY ->
            if(callbackWindow == window) {
                callback(Vec2(scaleX.toDouble(), scaleY.toDouble()))
            }
        }
    }

fun ContextGL.glfwSetWindowPosCallback(window: Long, callback: (pos: IVec2) -> Unit) =
    execute {
        GLFW.glfwSetWindowPosCallback(window) { callbackWindow, x, y ->
            if(callbackWindow == window) {
                callback(IVec2(x, y))
            }
        }
    }

fun ContextGL.glfwSetWindowFocusCallback(window: Long, callback: (focused: Boolean) -> Unit) =
    execute {
        GLFW.glfwSetWindowFocusCallback(window) { callbackWindow, focused ->
            if(callbackWindow == window) {
                callback(focused)
            }
        }
    }

fun ContextGL.glfwSetWindowMaximizeCallback(window: Long, callback: (maximized: Boolean) -> Unit) =
    execute {
        GLFW.glfwSetWindowMaximizeCallback(window) { callbackWindow, maximized ->
            if(callbackWindow == window) {
                callback(maximized)
            }
        }
    }

fun ContextGL.glfwSetWindowIconifyCallback(window: Long, callback: (minimized: Boolean) -> Unit) =
    execute {
        GLFW.glfwSetWindowIconifyCallback(window) { callbackWindow, minimized ->
           if(callbackWindow == window) {
               callback(minimized)
           }
        }
    }

fun ContextGL.glfwSetScrollCallback(window: Long, callback: (offset: Vec2) -> Unit) =
    execute {
        GLFW.glfwSetScrollCallback(window) { callbackWindow, x, y ->
            if(callbackWindow == window) {
                callback(Vec2(x, y))
            }
        }
    }

fun ContextGL.glfwSetCursorEnterCallback(window: Long, callback: (entered: Boolean) -> Unit) =
    execute {
        GLFW.glfwSetCursorEnterCallback(window) { callbackWindow, entered ->
            if(callbackWindow == window) {
                callback(entered)
            }
        }
    }

fun ContextGL.glfwSetCursorPosCallback(window: Long, callback: (pos: Vec2) -> Unit) =
    execute {
        GLFW.glfwSetCursorPosCallback(window) { callbackWindow, x, y ->
            if(callbackWindow == window) {
                callback(Vec2(x, y))
            }
        }
    }

fun ContextGL.glfwSetMouseButtonCallback(window: Long, callback: (button: MouseButton, action: Action, mods: Set<Mod>) -> Unit) =
    execute {
        GLFW.glfwSetMouseButtonCallback(window) { callbackWindow, button, action, mods ->
            if(callbackWindow == window) {
                callback(MouseButton.fromGl(button), Action.fromGl(action), Mod.fromGl(mods))
            }
        }
    }

fun ContextGL.glfwSetCharCallback(window: Long, callback: (codepoint: Char) -> Unit) =
    execute {
        GLFW.glfwSetCharCallback(window) { callbackWindow, codepoint ->
            if(callbackWindow == window) {
                callback(codepoint.toChar())
            }
        }
    }

fun ContextGL.glfwSetKeyCallback(window: Long, callback: (key: Key?, code: Int, action: Action, mods: Set<Mod>) -> Unit) =
    execute {
        GLFW.glfwSetKeyCallback(window) { callbackWindow, key, code, action, mods ->
            if(callbackWindow == window) {
                callback(Key.values().firstOrNull { it.code == key }, code, Action.fromGl(action), Mod.fromGl(mods))
            }
        }
    }

fun ContextGL.glfwSetCharModsCallback(window: Long, callback: (codepoint: Char, mods: Set<Mod>) -> Unit) =
    execute {
        GLFW.glfwSetCharModsCallback(window) { callbackWindow, codepoint, mods ->
            if(callbackWindow == window) {
                callback(codepoint.toChar(), Mod.fromGl(mods))
            }
        }
    }

fun ContextGL.glGenBuffers(): Int =
    execute {
        GL30C.glGenBuffers()
    }

fun ContextGL.glGenVertexArrays(): Int =
    execute {
        GL30C.glGenVertexArrays()
    }

fun ContextGL.checkGLErrors(errorFunction: ((error: Int) -> String?)? = null) {
    execute {
        checkGLErrorsTinn(errorFunction)
    }
}

fun ContextGL.glBindBuffer(target: Int, buffer: Int) =
    execute {
        GL30C.glBindBuffer(target, buffer)
    }

fun ContextGL.glBufferData(target: Int, size: Long, usage: Int) =
    execute {
        GL30C.glBufferData(target, size, usage)
    }

fun ContextGL.glVertexAttribPointer(index: Int, size: Int, type: Int, normalized: Boolean, stride: Int, pointer: Long) =
    execute {
        GL30C.glVertexAttribPointer(index, size, type, normalized, stride, pointer)
    }

fun ContextGL.glEnableVertexAttribArray(index: Int) =
    execute {
        GL30C.glEnableVertexAttribArray(index)
    }

fun ContextGL.glBindVertexArray(array: Int) =
    execute {
        GL30C.glBindVertexArray(array)
    }

fun ContextGL.glDeleteVertexArrays(array: Int) =
    execute {
        GL30C.glDeleteVertexArrays(array)
    }

fun ContextGL.glDeleteBuffers(buffer: Int) =
    execute {
        GL30C.glDeleteBuffers(buffer)
    }

fun ContextGL.glFramebufferTexture2D(target: Int, attachment: Int, texTarget: Int, texture: Int, level: Int) =
    execute {
        GL30C.glFramebufferTexture2D(target, attachment, texTarget, texture, level)
    }

fun ContextGL.glDeleteFramebuffers(frameBuffer: Int) =
    execute {
        GL30C.glDeleteFramebuffers(frameBuffer)
    }

fun ContextGL.glBindFramebuffer(target: Int, frameBuffer: Int) =
    execute {
        GL30C.glBindFramebuffer(target, frameBuffer)
    }

fun ContextGL.glDrawBuffers(attachments: IntArray) =
    execute {
        GL30C.glDrawBuffers(attachments)
    }

fun ContextGL.glViewport(pos: IVec2, size: IVec2) =
    execute {
        GL30C.glViewport(pos.x, pos.y, size.x, size.y)
    }

fun ContextGL.glfwGetMonitorName(monitor: Long): String? =
    execute {
        GLFW.glfwGetMonitorName(monitor)
    }

fun ContextGL.glfwGetPrimaryMonitor(): Long =
    execute {
        GLFW.glfwGetPrimaryMonitor()
    }

fun ContextGL.glfwGetVideoMode(monitor: Long): GLFWVidMode? =
    execute {
        GLFW.glfwGetVideoMode(monitor)
    }

fun ContextGL.glfwGetMonitorPos(monitor: Long): IVec2 =
    execute {
        MemoryStack.stackPush().use { stack ->
            val x = stack.mallocInt(1)
            val y = stack.mallocInt(1)

            GLFW.glfwGetMonitorPos(monitor, x, y)

            IVec2(x.get(), y.get())
        }
    }

fun ContextGL.glfwGetMonitorPhysicalSize(monitor: Long): IVec2 =
    execute {
        MemoryStack.stackPush().use { stack ->
            val width = stack.mallocInt(1)
            val height = stack.mallocInt(1)

            GLFW.glfwGetMonitorPhysicalSize(monitor, width, height)

            IVec2(width.get(), height.get())
        }
    }

fun ContextGL.glfwGetMonitorContentScale(monitor: Long): Vec2 =
    execute {
        MemoryStack.stackPush().use { stack ->
            val x = stack.mallocFloat(1)
            val y = stack.mallocFloat(1)

            GLFW.glfwGetMonitorContentScale(monitor, x, y)

            Vec2(x.get().toDouble(), y.get().toDouble())
        }
    }

fun ContextGL.glGenTextures(): Int =
    execute {
        GL30C.glGenTextures()
    }

fun ContextGL.glTexParameteri(target: Int, pname: Int, param: Int) =
    execute {
        GL30C.glTexParameteri(target, pname, param)
    }

fun ContextGL.glPixelStorei(pname: Int, param: Int) =
    execute {
        GL30C.glPixelStorei(pname, param)
    }

fun ContextGL.glTexImage2D(target: Int, level: Int, internalFormat: Int, width: Int, height: Int, border: Int, format: Int, type: Int, pixels: ByteBuffer?) =
    execute {
        GL30C.glTexImage2D(target, level, internalFormat, width, height, border, format, type, pixels)
    }

fun ContextGL.glTexImage2D(target: Int, level: Int, internalFormat: Int, width: Int, height: Int, border: Int, format: Int, type: Int, pixels: FloatBuffer?) =
    execute {
        GL30C.glTexImage2D(target, level, internalFormat, width, height, border, format, type, pixels)
    }

fun ContextGL.glTexImage2D(target: Int, level: Int, internalFormat: Int, width: Int, height: Int, border: Int, format: Int, type: Int) =
    glTexImage2D(target, level, internalFormat, width, height, border, format, type, null as ByteBuffer?)

fun ContextGL.glTexImage2DMultisample(target: Int, samples: Int, internalFormat: Int, width: Int, height: Int, fixedSampleLocations: Boolean) =
    execute {
        GL33C.glTexImage2DMultisample(target, samples, internalFormat, width, height, fixedSampleLocations)
    }

fun ContextGL.glGenerateMipmap(target: Int) =
    execute {
        GL30C.glGenerateMipmap(target)
    }

fun ContextGL.glActiveTexture(texture: Int) =
    execute {
        GL30C.glActiveTexture(texture)
    }

fun ContextGL.glBindTexture(target: Int, textureId: Int) =
    execute {
        GL30C.glBindTexture(target, textureId)
    }

fun ContextGL.glDeleteTextures(textureId: Int) =
    execute {
        GL30C.glDeleteTextures(textureId)
    }