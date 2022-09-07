package com.virusbear.tinn.studio

import com.virusbear.tinn.Driver
import com.virusbear.tinn.VertexFormat
import com.virusbear.tinn.imgui.ImGuiUIContext
import com.virusbear.tinn.math.Vec3
import com.virusbear.tinn.opengl.DriverGL
import com.virusbear.tinn.opengl.checkGLErrors
import imgui.ImGui
import org.intellij.lang.annotations.Language
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL13C.GL_TEXTURE0
import org.lwjgl.opengl.GL13C.glActiveTexture
import org.lwjgl.opengl.GL30C.*
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.system.MemoryUtil.NULL
import java.nio.ByteBuffer

fun main() {
    val window = initOpenGl()

    val context = ImGuiUIContext("#version 130", window)
    context.init()

    Driver.use(DriverGL())

    val tex = generateTexture()

    val vertices = floatArrayOf(
        -0.5f, -0.5f, 0.0f,
         0.5f, -0.5f, 0.0f,
         0.0f,  0.5f, 0.0f
    )

    val vb = Driver.driver.createVertexBuffer(3, VertexFormat().apply { position() }).apply {

    }
    val vbo = glGenBuffers()

    glBindBuffer(GL_ARRAY_BUFFER, vbo)
    checkGLErrors()
    glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW)
    glBindBuffer(GL_ARRAY_BUFFER, 0)

    //vertex Shader
    @Language("GLSL")
    val vsCode = """
        #version 330
        layout (location = 0) in vec3 aPos;
        
        void main()
        {
            gl_Position = vec4(aPos.x, aPos.y, aPos.z, 1.0);
        }
    """.trimIndent()

    val vs = glCreateShader(GL_VERTEX_SHADER)
    glShaderSource(vs, vsCode)
    glCompileShader(vs)
    //TODO: Get shader log
    checkGLErrors()

    //fragment Shader
    @Language("GLSL")
    val fsCode = """
        #version 330
        out vec4 FragColor;
        void main()
        {
            FragColor = vec4(1.0f, 0.5f, 0.2f, 1.0f);        
        }
    """.trimIndent()
    val fs = glCreateShader(GL_FRAGMENT_SHADER)
    glShaderSource(fs, fsCode)
    glCompileShader(fs)
    //TODO: Get shader log
    checkGLErrors()

    //shader program
    val shaderProgram = glCreateProgram()
    glAttachShader(shaderProgram, vs)
    glAttachShader(shaderProgram, fs)
    glLinkProgram(shaderProgram)
    //TODO: get link status
    checkGLErrors()

    glUseProgram(shaderProgram)
    checkGLErrors()

    val vao = glGenVertexArrays()
    glBindVertexArray(vao)
    glBindBuffer(GL_ARRAY_BUFFER, vbo)

    glVertexAttribPointer(0, 3, GL_FLOAT, false, 12, 0L)
    glEnableVertexAttribArray(0)
    glBindBuffer(GL_ARRAY_BUFFER, 0)

    glBindTexture(GL_TEXTURE_2D, tex)
    val fb = glGenFramebuffers()
    glBindFramebuffer(GL_FRAMEBUFFER, fb)
    glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, tex, 0)

    if(glCheckFramebufferStatus(GL_FRAMEBUFFER) == GL_FRAMEBUFFER_COMPLETE) println("Framebuffer ready for use")

    glDrawArrays(GL_TRIANGLES, 0, vertices.size)

    glBindFramebuffer(GL_FRAMEBUFFER, 0)
    glDeleteFramebuffers(fb)

    loop(window) {
        context.render {
            ImGui.begin("testImage")

            ImGui.image(tex, 600.0f, 400.0f)

            ImGui.end()
        }
    }

    terminateOpenGl(window)
}

private fun generateTexture(): Int {
    val texture = glGenTextures()
    checkGLErrors()

    glActiveTexture(GL_TEXTURE0)
    glBindTexture(GL_TEXTURE_2D, texture)
    checkGLErrors()

    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB16F, 600, 400, 0, GL_RGB, GL_HALF_FLOAT, null as ByteBuffer?)

    glBindTexture(GL_TEXTURE_2D, 0)
    checkGLErrors()

    return texture
}

private fun loop(window: Long, block: () -> Unit) {
    while (!glfwWindowShouldClose(window)) {
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)

        block()

        glfwSwapBuffers(window)
        glfwPollEvents()
    }
}

private fun terminateOpenGl(window: Long) {
    glfwDestroyWindow(window)
    glfwTerminate()
}

private fun initOpenGl(): Long {
    GLFWErrorCallback.createPrint(System.err).set()
    if (!glfwInit()) {
        throw IllegalStateException("Unable to initialize GLFW")
    }

    glfwDefaultWindowHints()
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)

    val window = glfwCreateWindow(800, 600, "Hello tinn!", NULL, NULL)
    if (window == NULL) {
        throw RuntimeException("Failed to create GLFW window")
    }

    glfwSetKeyCallback(window) { window, key, scancode, action, mods ->
        if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
            glfwSetWindowShouldClose(window, true)
    }


    val stack = stackPush()
    try {
        val pWidth = stack.mallocInt(1)
        val pHeight = stack.mallocInt(1)

        glfwGetWindowSize(window, pWidth, pHeight)

        glfwGetVideoMode(glfwGetPrimaryMonitor())?.let { vidmode ->
            glfwSetWindowPos(
                window,
                (vidmode.width() / 2) - pWidth.get(0) / 2,
                (vidmode.height() / 2) - pHeight.get(0) / 2
            )
        }
    } finally {
        stack.close()
    }

    glfwMakeContextCurrent(window)
    glfwSwapInterval(1)

    GL.createCapabilities()

    glClearColor(0.0f, 0.0f, 0.0f, 0.0f)
    return window
}