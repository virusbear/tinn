package com.virusbear.tinn.opengl

import com.virusbear.tinn.*
import com.virusbear.tinn.draw.Drawable
import com.virusbear.tinn.draw.Drawer
import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.shader.*
import com.virusbear.tinn.window.Window
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.withContext
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.system.MemoryUtil.NULL
import java.io.File
import java.util.concurrent.Executors

class DriverGL: Driver(), Context {
    override val dispatcher: CoroutineDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    override var currentContext: Context = this

    override fun init() {
        GLFWErrorCallback.createPrint(System.err).set()
        if (!execute { glfwInit() }) {
            throw IllegalStateException("Unable to initialize GLFW")
        }
    }

    private val destroyListeners = LinkedHashSet<Context.OnDestroyListener>()

    override fun onDestroy(onDestroyListener: Context.OnDestroyListener) {
        destroyListeners += onDestroyListener
    }

    override fun destroy() {
        if(destroyed)
            return

        destroyListeners.forEach(Context.OnDestroyListener::onDestroy)
        destroyListeners.clear()

        super.destroy()

        execute { glfwTerminate() }
    }

    override suspend fun pollEvents() {
        withContext(dispatcher) {
            async { glfwWaitEvents() }.await()
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
        WindowGL.create(width, height, title, resizable, vsync, multisample, this)

    override fun createDrawer(): Drawer =
        NanoVGDrawer(currentContext)

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
            levels,
            currentContext
        )

    override fun loadImage(file: File, format: ColorFormat): ColorBuffer =
        ColorBufferGL.loadImage(file, format, currentContext)

    override fun createDepthBuffer(width: Int, height: Int): DepthBuffer {
        TODO("Prio 5")
    }

    override fun createIndexBuffer(size: Int): IndexBuffer =
        IndexBufferGL(
            size,
            currentContext
        )

    override fun createVertexBuffer(size: Int, format: VertexFormat): VertexBuffer =
        VertexBufferGL(
            size,
            format,
            currentContext
        )

    override fun createRenderTarget(
        width: Int, 
        height: Int, 
        contentScale: Double
    ): RenderTarget =
        RenderTargetGL(
            width, height, contentScale, null, currentContext
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
}