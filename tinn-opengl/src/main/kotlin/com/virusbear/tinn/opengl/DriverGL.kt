package com.virusbear.tinn.opengl

import com.virusbear.tinn.*
import com.virusbear.tinn.draw.Drawable
import com.virusbear.tinn.draw.Drawer
import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.shader.*
import com.virusbear.tinn.window.Window
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.system.MemoryUtil.NULL
import java.io.File
import java.util.LinkedList

class DriverGL: Driver() {
    override fun init() {
        GLFWErrorCallback.createPrint(System.err).set()
        if (!glfwInit()) {
            throw IllegalStateException("Unable to initialize GLFW")
        }
    }

    override fun destroy() {
        if(destroyed)
            return

        super.destroy()

        glfwTerminate()
    }

    override suspend fun pollEvents() {
        for(window in _windows) {
            window.pollEvents()
        }
    }

    override val availableMonitors: List<Monitor>
        get() =
            glfwGetMonitors()?.let { buffer ->
                buildList {
                    while(buffer.hasRemaining()) {
                        add(MonitorGL(buffer.get()))
                    }
                }
            } ?: emptyList()

    override val primaryMonitor: Monitor
        get() = glfwGetPrimaryMonitor().let { native ->
            if(native == NULL) {
                error("No primary monitor available")
            } else {
                MonitorGL(native)
            }
        }

    override fun createWindow(
        width: Int,
        height: Int,
        title: String,
        resizable: Boolean,
        vsync: Boolean,
        multisample: MultiSample
    ): Window =
        WindowGL.create(width, height, title, resizable, vsync, multisample).also {
            _windows += it as WindowGL
        }

    private val _windows: MutableList<WindowGL> = LinkedList()

    override fun createDrawer(): Drawer =
        NanoVGDrawer()

    override fun createDrawable(size: Vec2, block: Drawer.() -> Unit): Drawable =
        DrawableGL(size, block)

    override fun createColorBuffer(
        width: Int,
        height: Int,
        contentScale: Double,
        format: ColorFormat,
        multisample: MultiSample,
        levels: MipMapLevel
    ): ColorBuffer =
        ColorBufferGL(
            width,
            height,
            contentScale,
            format,
            multisample,
            levels
        )

    override fun loadImage(file: File, format: ColorFormat): ColorBuffer =
        ColorBufferGL.loadImage(file, format)

    override fun createDepthBuffer(width: Int, height: Int): DepthBuffer {
        TODO("Prio 5")
    }

    override fun createIndexBuffer(size: Int): IndexBuffer =
        IndexBufferGL(
            size
        )

    override fun createVertexBuffer(size: Int, format: VertexFormat): VertexBuffer =
        VertexBufferGL(
            size,
            format
        )

    override fun createRenderTarget(
        width: Int, 
        height: Int, 
        contentScale: Double
    ): RenderTarget =
        RenderTargetGL(
            width, height, contentScale
        )

    override fun createComputeShader(code: String): ComputeShader {
        TODO("Not yet implemented")
    }

    override fun createEvaluationShader(code: String): EvaluationShader {
        TODO("Not yet implemented")
    }

    override fun createFragmentShader(code: String): FragmentShader {
        TODO("Prio 2")
    }

    override fun createGeometryShader(code: String): GeometryShader {
        TODO("Not yet implemented")
    }

    override fun createShaderProgram(): ShaderProgram {
        TODO("Prio 3")
    }

    override fun createTesselationControlShader(code: String): TesselationControlShader {
        TODO("Not yet implemented")
    }

    override fun createVertexShader(code: String): VertexShader {
        TODO("Prio 1")
    }

    override val activeRenderTarget: RenderTarget
        get() = RenderTargetGL.activeRenderTarget

    override fun untrack(trackable: Trackable) {
        super.untrack(trackable)

        if(trackable is WindowGL) {
            _windows -= trackable
        }
    }
}