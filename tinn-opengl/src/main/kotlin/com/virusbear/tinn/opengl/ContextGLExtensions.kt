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

fun GraphicsContextGL.glfwDefaultWindowHints() =
    execute {
        GLFW.glfwDefaultWindowHints()
    }

fun GraphicsContextGL.glfwWindowHint(hint: Int, value: Int) =
    execute {
        GLFW.glfwWindowHint(hint, value)
    }

fun GraphicsContextGL.glfwCreateWindow(width: Int, height: Int, title: String, monitor: Long, share: Long): Long =
    execute {
        GLFW.glfwCreateWindow(width, height, title, monitor, share)
    }

fun GraphicsContextGL.glfwGetWindowSize(window: Long): IVec2 =
    execute {
        MemoryStack.stackPush().use { stack ->
            val pWidth = stack.mallocInt(1)
            val pHeight = stack.mallocInt(1)

            GLFW.glfwGetWindowSize(window, pWidth, pHeight)

            IVec2(pWidth.get(), pHeight.get())
        }
    }

fun GraphicsContextGL.glfwSetWindowPos(window: Long, pos: IVec2) =
    execute {
        GLFW.glfwSetWindowPos(window, pos.x, pos.y)
    }

fun GraphicsContextGL.glfwSwapInterval(interval: Int) =
    execute {
        GLFW.glfwSwapInterval(interval)
    }

fun GraphicsContextGL.createCapabilities(): GLCapabilities {
    return execute {
        GL.createCapabilities()
    }
}

fun GraphicsContextGL.glClearColor(color: Color) {
    execute {
        GL30C.glClearColor(color.r.toFloat(), color.g.toFloat(), color.b.toFloat(), color.a.toFloat())
    }
}

fun GraphicsContextGL.glfwWindowShouldClose(window: Long): Boolean =
    execute {
        GLFW.glfwWindowShouldClose(window)
    }

fun GraphicsContextGL.glClear(mask: Int) =
    execute {
        GL30C.glClear(mask)
    }

fun GraphicsContextGL.glfwSwapBuffers(window: Long) =
    execute {
        GLFW.glfwSwapBuffers(window)
    }

fun GraphicsContextGL.glfwPollEvents() =
    execute {
        GLFW.glfwPollEvents()
    }

fun GraphicsContextGL.glfwDestroyWindow(window: Long) =
    execute {
        GLFW.glfwDestroyWindow(window)
    }

fun GraphicsContextGL.glfwGetWindowContentScale(window: Long): Vec2 =
    execute {
        MemoryStack.stackPush().use { stack ->
            val scaleX = stack.mallocFloat(1)
            val scaleY = stack.mallocFloat(1)

            GLFW.glfwGetWindowContentScale(window, scaleX, scaleY)

            Vec2(scaleX.get().toDouble(), scaleY.get().toDouble())
        }
    }

fun GraphicsContextGL.glfwGetWindowMonitor(window: Long): Long =
    execute {
        GLFW.glfwGetWindowMonitor(window)
    }

fun GraphicsContextGL.glfwGetWindowPos(window: Long): IVec2 =
    execute {
        MemoryStack.stackPush().use { stack ->
            val xPos = stack.mallocInt(1)
            val yPos = stack.mallocInt(1)

            GLFW.glfwGetWindowPos(window, xPos, yPos)

            IVec2(xPos.get(), yPos.get())
        }
    }

fun GraphicsContextGL.glfwSetWindowSizeCallback(window: Long, callback: (size: IVec2) -> Unit) =
    execute {
        GLFW.glfwSetWindowSizeCallback(window) { callbackWindow, width, height ->
            if(callbackWindow == window) {
                callback(IVec2(width, height))
            }
        }
    }

fun GraphicsContextGL.glfwSetWindowContentScaleCallback(window: Long, callback: (scale: Vec2) -> Unit) =
    execute {
        GLFW.glfwSetWindowContentScaleCallback(window) { callbackWindow, scaleX, scaleY ->
            if(callbackWindow == window) {
                callback(Vec2(scaleX.toDouble(), scaleY.toDouble()))
            }
        }
    }

fun GraphicsContextGL.glfwSetWindowPosCallback(window: Long, callback: (pos: IVec2) -> Unit) =
    execute {
        GLFW.glfwSetWindowPosCallback(window) { callbackWindow, x, y ->
            if(callbackWindow == window) {
                callback(IVec2(x, y))
            }
        }
    }

fun GraphicsContextGL.glfwSetWindowFocusCallback(window: Long, callback: (focused: Boolean) -> Unit) =
    execute {
        GLFW.glfwSetWindowFocusCallback(window) { callbackWindow, focused ->
            if(callbackWindow == window) {
                callback(focused)
            }
        }
    }

fun GraphicsContextGL.glfwSetWindowMaximizeCallback(window: Long, callback: (maximized: Boolean) -> Unit) =
    execute {
        GLFW.glfwSetWindowMaximizeCallback(window) { callbackWindow, maximized ->
            if(callbackWindow == window) {
                callback(maximized)
            }
        }
    }

fun GraphicsContextGL.glfwSetWindowIconifyCallback(window: Long, callback: (minimized: Boolean) -> Unit) =
    execute {
        GLFW.glfwSetWindowIconifyCallback(window) { callbackWindow, minimized ->
           if(callbackWindow == window) {
               callback(minimized)
           }
        }
    }

fun GraphicsContextGL.glfwSetScrollCallback(window: Long, callback: (offset: Vec2) -> Unit) =
    execute {
        GLFW.glfwSetScrollCallback(window) { callbackWindow, x, y ->
            if(callbackWindow == window) {
                callback(Vec2(x, y))
            }
        }
    }

fun GraphicsContextGL.glfwSetCursorEnterCallback(window: Long, callback: (entered: Boolean) -> Unit) =
    execute {
        GLFW.glfwSetCursorEnterCallback(window) { callbackWindow, entered ->
            if(callbackWindow == window) {
                callback(entered)
            }
        }
    }

fun GraphicsContextGL.glfwSetCursorPosCallback(window: Long, callback: (pos: Vec2) -> Unit) =
    execute {
        GLFW.glfwSetCursorPosCallback(window) { callbackWindow, x, y ->
            if(callbackWindow == window) {
                callback(Vec2(x, y))
            }
        }
    }

fun GraphicsContextGL.glfwSetMouseButtonCallback(window: Long, callback: (button: MouseButton, action: Action, mods: Set<Mod>) -> Unit) =
    execute {
        GLFW.glfwSetMouseButtonCallback(window) { callbackWindow, button, action, mods ->
            if(callbackWindow == window) {
                callback(MouseButton.fromGl(button), Action.fromGl(action), Mod.fromGl(mods))
            }
        }
    }

fun GraphicsContextGL.glfwSetCharCallback(window: Long, callback: (codepoint: Char) -> Unit) =
    execute {
        GLFW.glfwSetCharCallback(window) { callbackWindow, codepoint ->
            if(callbackWindow == window) {
                callback(codepoint.toChar())
            }
        }
    }

fun GraphicsContextGL.glfwSetKeyCallback(window: Long, callback: (key: Key?, code: Int, action: Action, mods: Set<Mod>) -> Unit) =
    execute {
        GLFW.glfwSetKeyCallback(window) { callbackWindow, key, code, action, mods ->
            if(callbackWindow == window) {
                callback(Key.values().firstOrNull { it.code == key }, code, Action.fromGl(action), Mod.fromGl(mods))
            }
        }
    }

fun GraphicsContextGL.glfwSetCharModsCallback(window: Long, callback: (codepoint: Char, mods: Set<Mod>) -> Unit) =
    execute {
        GLFW.glfwSetCharModsCallback(window) { callbackWindow, codepoint, mods ->
            if(callbackWindow == window) {
                callback(codepoint.toChar(), Mod.fromGl(mods))
            }
        }
    }

fun GraphicsContextGL.glGenBuffers(): Int =
    execute {
        GL30C.glGenBuffers()
    }

fun GraphicsContextGL.glGenVertexArrays(): Int =
    execute {
        GL30C.glGenVertexArrays()
    }

fun GraphicsContextGL.checkGLErrors(errorFunction: ((error: Int) -> String?)? = null) {
    execute {
        checkGLErrorsTinn(errorFunction)
    }
}

fun GraphicsContextGL.glBindBuffer(target: Int, buffer: Int) =
    execute {
        GL30C.glBindBuffer(target, buffer)
    }

fun GraphicsContextGL.glBufferData(target: Int, size: Long, usage: Int) =
    execute {
        GL30C.glBufferData(target, size, usage)
    }

fun GraphicsContextGL.glVertexAttribPointer(index: Int, size: Int, type: Int, normalized: Boolean, stride: Int, pointer: Long) =
    execute {
        GL30C.glVertexAttribPointer(index, size, type, normalized, stride, pointer)
    }

fun GraphicsContextGL.glEnableVertexAttribArray(index: Int) =
    execute {
        GL30C.glEnableVertexAttribArray(index)
    }

fun GraphicsContextGL.glBindVertexArray(array: Int) =
    execute {
        GL30C.glBindVertexArray(array)
    }

fun GraphicsContextGL.glDeleteVertexArrays(array: Int) =
    execute {
        GL30C.glDeleteVertexArrays(array)
    }

fun GraphicsContextGL.glDeleteBuffers(buffer: Int) =
    execute {
        GL30C.glDeleteBuffers(buffer)
    }

fun GraphicsContextGL.glFramebufferTexture2D(target: Int, attachment: Int, texTarget: Int, texture: Int, level: Int) =
    execute {
        GL30C.glFramebufferTexture2D(target, attachment, texTarget, texture, level)
    }

fun GraphicsContextGL.glDeleteFramebuffers(frameBuffer: Int) =
    execute {
        GL30C.glDeleteFramebuffers(frameBuffer)
    }

fun GraphicsContextGL.glBindFramebuffer(target: Int, frameBuffer: Int) =
    execute {
        GL30C.glBindFramebuffer(target, frameBuffer)
    }

fun GraphicsContextGL.glDrawBuffers(attachments: IntArray) =
    execute {
        GL30C.glDrawBuffers(attachments)
    }

fun GraphicsContextGL.glViewport(pos: IVec2, size: IVec2) =
    execute {
        GL30C.glViewport(pos.x, pos.y, size.x, size.y)
    }

fun GraphicsContextGL.glfwGetMonitorName(monitor: Long): String? =
    execute {
        GLFW.glfwGetMonitorName(monitor)
    }

fun GraphicsContextGL.glfwGetPrimaryMonitor(): Long =
    execute {
        GLFW.glfwGetPrimaryMonitor()
    }

fun GraphicsContextGL.glfwGetVideoMode(monitor: Long): GLFWVidMode? =
    execute {
        GLFW.glfwGetVideoMode(monitor)
    }

fun GraphicsContextGL.glfwGetMonitorPos(monitor: Long): IVec2 =
    execute {
        MemoryStack.stackPush().use { stack ->
            val x = stack.mallocInt(1)
            val y = stack.mallocInt(1)

            GLFW.glfwGetMonitorPos(monitor, x, y)

            IVec2(x.get(), y.get())
        }
    }

fun GraphicsContextGL.glfwGetMonitorPhysicalSize(monitor: Long): IVec2 =
    execute {
        MemoryStack.stackPush().use { stack ->
            val width = stack.mallocInt(1)
            val height = stack.mallocInt(1)

            GLFW.glfwGetMonitorPhysicalSize(monitor, width, height)

            IVec2(width.get(), height.get())
        }
    }

fun GraphicsContextGL.glfwGetMonitorContentScale(monitor: Long): Vec2 =
    execute {
        MemoryStack.stackPush().use { stack ->
            val x = stack.mallocFloat(1)
            val y = stack.mallocFloat(1)

            GLFW.glfwGetMonitorContentScale(monitor, x, y)

            Vec2(x.get().toDouble(), y.get().toDouble())
        }
    }

fun GraphicsContextGL.glGenTextures(): Int =
    execute {
        GL30C.glGenTextures()
    }

fun GraphicsContextGL.glTexParameteri(target: Int, pname: Int, param: Int) =
    execute {
        GL30C.glTexParameteri(target, pname, param)
    }

fun GraphicsContextGL.glPixelStorei(pname: Int, param: Int) =
    execute {
        GL30C.glPixelStorei(pname, param)
    }

fun GraphicsContextGL.glTexImage2D(target: Int, level: Int, internalFormat: Int, width: Int, height: Int, border: Int, format: Int, type: Int, pixels: ByteBuffer?) =
    execute {
        GL30C.glTexImage2D(target, level, internalFormat, width, height, border, format, type, pixels)
    }

fun GraphicsContextGL.glTexImage2D(target: Int, level: Int, internalFormat: Int, width: Int, height: Int, border: Int, format: Int, type: Int, pixels: FloatBuffer?) =
    execute {
        GL30C.glTexImage2D(target, level, internalFormat, width, height, border, format, type, pixels)
    }

fun GraphicsContextGL.glTexImage2D(target: Int, level: Int, internalFormat: Int, width: Int, height: Int, border: Int, format: Int, type: Int) =
    glTexImage2D(target, level, internalFormat, width, height, border, format, type, null as ByteBuffer?)

fun GraphicsContextGL.glTexImage2DMultisample(target: Int, samples: Int, internalFormat: Int, width: Int, height: Int, fixedSampleLocations: Boolean) =
    execute {
        GL33C.glTexImage2DMultisample(target, samples, internalFormat, width, height, fixedSampleLocations)
    }

fun GraphicsContextGL.glGenerateMipmap(target: Int) =
    execute {
        GL30C.glGenerateMipmap(target)
    }

fun GraphicsContextGL.glActiveTexture(texture: Int) =
    execute {
        GL30C.glActiveTexture(texture)
    }

fun GraphicsContextGL.glBindTexture(target: Int, textureId: Int) =
    execute {
        GL30C.glBindTexture(target, textureId)
    }

fun GraphicsContextGL.glDeleteTextures(textureId: Int) =
    execute {
        GL30C.glDeleteTextures(textureId)
    }