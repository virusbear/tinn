package com.virusbear.tinn

import com.virusbear.tinn.draw.Drawable
import com.virusbear.tinn.draw.Drawer
import com.virusbear.tinn.shader.*
import java.io.File

abstract class Driver: Destroyable {
    abstract fun init()

    abstract fun createWindow(
        width: Int,
        height: Int,
        title: String,
        resizable: Boolean,
        vsync: Boolean,
        multisample: MultiSample
    ): Window

    abstract fun createDrawer(): Drawer
    abstract fun createDrawable(block: Drawer.() -> Unit): Drawable

    abstract fun createColorBuffer(
        width: Int,
        height: Int,
        contentScale: Double,
        format: ColorFormat,
        multisample: MultiSample,
        levels: MipMapLevel
    ): ColorBuffer
    abstract fun loadImage(
        file: File,
        format: ColorFormat
    ): ColorBuffer

    abstract fun createDepthBuffer(width: Int, height: Int): DepthBuffer
    abstract fun createIndexBuffer(size: Int): IndexBuffer
    abstract fun createVertexBuffer(size: Int, format: VertexFormat): VertexBuffer
    abstract fun createRenderTarget(width: Int, height: Int, contentScale: Double): RenderTarget
    abstract fun createComputeShader(code: String): ComputeShader
    abstract fun createEvaluationShader(code: String): EvaluationShader
    abstract fun createFragmentShader(code: String): FragmentShader
    abstract fun createGeometryShader(code: String): GeometryShader
    abstract fun createShaderProgram(): ShaderProgram
    abstract fun createTesselationControlShader(code: String): TesselationControlShader
    abstract fun createVertexShader(code: String): VertexShader
    //TODO: add loadShader(binary: ShaderBinary): Shader?
    //TODO: add loadShaderProgram(binary: ShaderProgramBinary): ShaderProgram?

    abstract val activeRenderTarget: RenderTarget

    final override var destroyed: Boolean = false
        private set
    private val tracked = mutableSetOf<Trackable>()

    fun track(trackable: Trackable) {
        tracked += trackable
    }
    fun untrack(trackable: Trackable) {
        tracked -= trackable
    }

    override fun destroy() {
        while(true) {
            when(val e = tracked.firstOrNull()) {
                null -> break
                else -> e.destroy()
            }
        }

        destroyed = true
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
            Driver.use(driver)
            val result = driver.block()
            Driver.use(currentDriver)
            return result
        }
    }
}

