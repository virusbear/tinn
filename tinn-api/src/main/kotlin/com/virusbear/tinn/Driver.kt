package com.virusbear.tinn

import com.virusbear.tinn.draw.Drawable
import com.virusbear.tinn.draw.Drawer
import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.shader.*
import com.virusbear.tinn.window.Window
import java.io.File

abstract class Driver: BaseDestroyable() {
    abstract fun init()

    suspend fun runEventLoop() {
        while(!destroyed) {
            pollEvents()
        }
    }

    protected abstract suspend fun pollEvents()

    abstract val availableMonitors: List<Monitor>
    abstract val primaryMonitor: Monitor

    abstract fun createContext(): GraphicsContext

    abstract fun createWindow(
        width: Int,
        height: Int,
        title: String,
        resizable: Boolean,
        vsync: Boolean,
        multisample: MultiSample,
        context: GraphicsContext
    ): Window

    abstract fun createDrawer(context: GraphicsContext): Drawer
    abstract fun createDrawable(size: Vec2, block: Drawer.() -> Unit): Drawable

    abstract fun createColorBuffer(
        width: Int,
        height: Int,
        contentScale: Double,
        format: ColorFormat,
        multisample: MultiSample,
        levels: MipMapLevel,
        context: GraphicsContext
    ): ColorBuffer
    abstract fun loadImage(
        file: File,
        format: ColorFormat,
        context: GraphicsContext
    ): ColorBuffer

    abstract fun createDepthBuffer(width: Int, height: Int, context: GraphicsContext): DepthBuffer
    abstract fun createIndexBuffer(size: Int, context: GraphicsContext): IndexBuffer
    abstract fun createVertexBuffer(size: Int, format: VertexFormat, context: GraphicsContext): VertexBuffer
    abstract fun createRenderTarget(width: Int, height: Int, contentScale: Double, context: GraphicsContext): RenderTarget
    abstract fun createComputeShader(code: String, context: GraphicsContext): ComputeShader
    abstract fun createEvaluationShader(code: String, context: GraphicsContext): EvaluationShader
    abstract fun createFragmentShader(code: String, context: GraphicsContext): FragmentShader
    abstract fun createGeometryShader(code: String, context: GraphicsContext): GeometryShader
    abstract fun createShaderProgram(context: GraphicsContext): ShaderProgram
    abstract fun createTesselationControlShader(code: String, context: GraphicsContext): TesselationControlShader
    abstract fun createVertexShader(code: String, context: GraphicsContext): VertexShader
    //TODO: add loadShader(binary: ShaderBinary): Shader?
    //TODO: add loadShaderProgram(binary: ShaderProgramBinary): ShaderProgram?

    abstract fun activeRenderTarget(context: GraphicsContext): RenderTarget

    private val tracked = mutableSetOf<Trackable>()

    open fun track(trackable: Trackable) {
        tracked += trackable
    }
    open fun untrack(trackable: Trackable) {
        tracked -= trackable
    }

    override fun destroy() {
        while(true) {
            when(val e = tracked.firstOrNull()) {
                null -> break
                else -> e.destroy()
            }
        }

        super.destroy()
    }

    companion object {
        private var instance: Driver? = null
        val driver: Driver
            get() = instance ?: error("No driver instance set")

        fun use(driver: Driver) {
            instance?.let {
                if(!it.destroyed) error("Driver already set. Changing drivers is not allowed.")
            }

            if(driver.destroyed) error("Unable to use destroyed driver.")

            instance = driver
        }

        inline fun <T> use(block: Driver.() -> T): T =
            driver.block()

        inline fun <T> using(driver: Driver, block: Driver.() -> T): T {
            val currentDriver = Driver.driver
            use(driver)
            val result = driver.block()
            use(currentDriver)
            return result
        }
    }
}