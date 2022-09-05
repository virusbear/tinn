package com.virusbear.tinn.studio

import com.virusbear.tinn.*
import com.virusbear.tinn.imgui.ImGuiUIContext
import com.virusbear.tinn.opengl.ColorBufferGL
import com.virusbear.tinn.opengl.DriverGL
import com.virusbear.tinn.opengl.VertexBufferGL
import imgui.ImGui
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.system.MemoryUtil.NULL

fun main() {
    GLFWErrorCallback.createPrint(System.err).set()
    if(!glfwInit()) {
        throw IllegalStateException("Unable to initialize GLFW")
    }

    glfwDefaultWindowHints()
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)

    val window = glfwCreateWindow(800, 600, "Hello tinn!", NULL, NULL)
    if(window == NULL) {
        throw RuntimeException("Failed to create GLFW window")
    }

    glfwSetKeyCallback(window) { window, key, scancode, action, mods ->
        if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
            glfwSetWindowShouldClose(window, true)
    }


    val stack = stackPush()
    try {
        val pWidth = stack.mallocInt(1)
        val pHeight = stack.mallocInt(1)

        glfwGetWindowSize(window, pWidth, pHeight)

        glfwGetVideoMode(glfwGetPrimaryMonitor())?.let { vidmode ->
            glfwSetWindowPos(window, (vidmode.width() / 2) - pWidth.get(0) / 2, (vidmode.height() / 2) - pHeight.get(0) / 2)
        }
    } finally {
        stack.close()
    }

    glfwMakeContextCurrent(window)
    glfwSwapInterval(1)

    GL.createCapabilities()

    glClearColor(0.0f, 0.0f, 0.0f, 0.0f)

    val panel = ParametersPanel("Parameters")
    val context = ImGuiUIContext("#version 130", window)

    context.init()

    Driver.use(DriverGL())

    val cb = Driver.driver.createColorBuffer(400, 600, ColorFormat.RGB8, MultiSample.Count(8u), MipMapLevel.Count(7u))

    cb.bound {  }

    cb.destroy()

    val vb = Driver.driver.createVertexBuffer(400000000, VertexFormat().apply { position(); normal(); color(); texCoord() })
    vb.bound {  }
    vb.destroy()

    while(!glfwWindowShouldClose(window)) {
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)

        context.render {
            ImGui.begin(panel.name)
            panel.render(context)
            ImGui.end()
        }

        glfwSwapBuffers(window)
        glfwPollEvents()
    }

    glfwDestroyWindow(window)
    glfwTerminate()
}