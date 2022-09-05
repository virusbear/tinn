package com.virusbear.tinn.opengl

import com.virusbear.tinn.*
import com.virusbear.tinn.shader.*

class DriverGL: Driver {
    override var destroyed: Boolean = false
    private set

    override fun destroy() {
        //TODO: destroy driver here

        destroyed = true
    }

    override fun createColorBuffer(
        width: Int,
        height: Int,
        format: ColorFormat,
        multisample: MultiSample,
        levels: MipMapLevel
    ): ColorBuffer =
        ColorBufferGL(
            width,
            height,
            format,
            multisample,
            levels
        )

    override fun createDepthBuffer(width: Int, height: Int): DepthBuffer {
        TODO("Not yet implemented")
    }

    override fun createIndexBuffer(size: Int): IndexBuffer {
        TODO("Not yet implemented")
    }

    override fun createVertexBuffer(size: Int, format: VertexFormat): VertexBuffer =
        VertexBufferGL(size, format)

    override fun createRenderTarget(width: Int, height: Int) {
        TODO("Not yet implemented")
    }

    override fun createComputeShader(code: String): ComputeShader {
        TODO("Not yet implemented")
    }

    override fun createEvaluationShader(code: String): EvaluationShader {
        TODO("Not yet implemented")
    }

    override fun createFragmentShader(code: String): FragmentShader {
        TODO("Not yet implemented")
    }

    override fun createGeometryShader(code: String): GeometryShader {
        TODO("Not yet implemented")
    }

    override fun createShaderProgram(): ShaderProgram {
        TODO("Not yet implemented")
    }

    override fun createTesselationControlShader(code: String): TesselationControlShader {
        TODO("Not yet implemented")
    }

    override fun createVertexShader(code: String): VertexShader {
        TODO("Not yet implemented")
    }
}