package com.virusbear.tinn

import com.virusbear.tinn.shader.*

interface Driver: Destroyable {
    fun createColorBuffer(
        width: Int,
        height: Int,
        format: ColorFormat,
        multisample: MultiSample,
        levels: MipMapLevel
    ): ColorBuffer

    fun createDepthBuffer(width: Int, height: Int): DepthBuffer
    fun createIndexBuffer(size: Int): IndexBuffer
    fun createVertexBuffer(size: Int, format: VertexFormat): VertexBuffer
    fun createRenderTarget(width: Int, height: Int) //TODO: add attachments (colorbuffers, depthbuffers)
    fun createComputeShader(code: String): ComputeShader
    fun createEvaluationShader(code: String): EvaluationShader
    fun createFragmentShader(code: String): FragmentShader
    fun createGeometryShader(code: String): GeometryShader
    fun createShaderProgram(): ShaderProgram
    fun createTesselationControlShader(code: String): TesselationControlShader
    fun createVertexShader(code: String): VertexShader
    //TODO: add loadShader(binary: ShaderBinary): Shader?
    //TODO: add loadShaderProgram(binary: ShaderProgramBinary): ShaderProgram?

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

