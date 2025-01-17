package com.virusbear.tinn

import com.virusbear.tinn.shader.*

abstract class Driver: Destroyable {
    abstract fun createColorBuffer(
        width: Int,
        height: Int,
        format: ColorFormat,
        multisample: MultiSample,
        levels: MipMapLevel
    ): ColorBuffer

    abstract fun createDepthBuffer(width: Int, height: Int): DepthBuffer
    abstract fun createIndexBuffer(size: Int): IndexBuffer
    abstract fun createVertexBuffer(size: Int, format: VertexFormat): VertexBuffer
    abstract fun createRenderTarget(width: Int, height: Int) //TODO: add attachments (colorbuffers, depthbuffers)
    abstract fun createComputeShader(code: String): ComputeShader
    abstract fun createEvaluationShader(code: String): EvaluationShader
    abstract fun createFragmentShader(code: String): FragmentShader
    abstract fun createGeometryShader(code: String): GeometryShader
    abstract fun createShaderProgram(): ShaderProgram
    abstract fun createTesselationControlShader(code: String): TesselationControlShader
    abstract fun createVertexShader(code: String): VertexShader
    //TODO: add loadShader(binary: ShaderBinary): Shader?
    //TODO: add loadShaderProgram(binary: ShaderProgramBinary): ShaderProgram?

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
        tracked.forEach(Trackable::destroy)

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
    }
}

