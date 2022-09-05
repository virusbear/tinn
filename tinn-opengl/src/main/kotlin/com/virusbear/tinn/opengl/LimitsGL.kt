package com.virusbear.tinn.opengl

import org.lwjgl.opengl.GL30C.*
import org.lwjgl.opengl.GL31C.*
import org.lwjgl.opengl.GL32C.GL_MAX_SERVER_WAIT_TIMEOUT
import org.lwjgl.opengl.GL32C.GL_MAX_VERTEX_OUTPUT_COMPONENTS
import org.lwjgl.opengl.GL41C.*
import org.lwjgl.opengl.GL43C.GL_MAX_ELEMENT_INDEX
import org.lwjgl.opengl.GL43C.GL_MAX_FRAGMENT_INPUT_COMPONENTS

class InvalidGLLimitException(name: String, value: Number, minimum: Number): Exception("Invalid $name ($value): must be at least $minimum")

object LimitsGL {
    private fun requireLimit(name: String, value: Int, minimum: Int) {
        if(value < minimum) {
            throw InvalidGLLimitException(name, value, minimum)
        }
    }

    private fun requireLimit(name: String, value: Float, minimum: Float) {
        if(value < minimum) {
            throw InvalidGLLimitException(name, value, minimum)
        }
    }

    val Max3dTextureSize by lazy {
        glGetInteger(GL_MAX_3D_TEXTURE_SIZE).also { value ->
            requireLimit("GL_MAX_3D_TEXTURE_SIZE", value, 3)
        }
    }

    val MaxArrayTextureLayers by lazy {
        glGetInteger(GL_MAX_ARRAY_TEXTURE_LAYERS).also { value ->
            requireLimit("GL_MAX_ARRAY_TEXTURE_LAYERS", value, 256)
        }
    }

    val MaxColorAttachments by lazy {
        glGetInteger(GL_MAX_COLOR_ATTACHMENTS).also { value ->
            requireLimit("GL_MAX_COLOR_ATTACHMENTS", value, 4)
        }
    }

    val MaxCombinedFragmentUniformComponents by lazy {
        glGetInteger(GL_MAX_COMBINED_FRAGMENT_UNIFORM_COMPONENTS).also { value ->
            requireLimit(
                "GL_MAX_COMBINED_FRAGMENT_UNIFORM_COMPONENTS",
                value,
                MaxFragmentUniformComponents + MaxUniformBlockSize * MaxFragmentUniformBlocks / 4
            )
        }
    }

    val MaxCombinedTextureImageUnits by lazy {
        glGetInteger(GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS).also { value ->
            requireLimit("GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS", value, 32)
        }
    }

    val MaxCombinedUniformBlocks by lazy {
        glGetInteger(GL_MAX_COMBINED_UNIFORM_BLOCKS).also { value ->
            requireLimit("GL_MAX_COMBINED_UNIFORM_BLOCKS", value, 24)
        }
    }

    val MaxCombinedVertexUniformComponents by lazy {
        glGetInteger(GL_MAX_COMBINED_VERTEX_UNIFORM_COMPONENTS).also { value ->
            requireLimit(
                "GL_MAX_COMBINED_VERTEX_UNIFORM_COMPONENTS",
                value,
                GL_MAX_VERTEX_UNIFORM_COMPONENTS + GL_MAX_UNIFORM_BLOCK_SIZE * GL_MAX_VERTEX_UNIFORM_BLOCKS / 4
            )
        }
    }

    val MaxCubeMapTextureSize by lazy {
        glGetInteger(GL_MAX_CUBE_MAP_TEXTURE_SIZE).also { value ->
            requireLimit("GL_MAX_CUBE_MAP_TEXTURE_SIZE", value, 2048)
        }
    }

    val MaxDrawBuffers by lazy {
        glGetInteger(GL_MAX_DRAW_BUFFERS).also { value ->
            requireLimit("GL_MAX_DRAW_BUFFERS", value, 4)
        }
    }

    val MaxElementIndex by lazy {
        glGetInteger(GL_MAX_ELEMENT_INDEX).also { value ->
            requireLimit("GL_MAX_ELEMENT_INDEX", value, (2 shl 24) - 1)
        }
    }

    val MaxElementsIndices by lazy {
        glGetInteger(GL_MAX_ELEMENTS_INDICES)
    }

    val MaxElementsVertices by lazy {
        glGetInteger(GL_MAX_ELEMENTS_VERTICES)
    }

    val MaxFragmentInputComponents by lazy {
        glGetInteger(GL_MAX_FRAGMENT_INPUT_COMPONENTS).also { value ->
            requireLimit("GL_MAX_FRAGMENT_INPUT_COMPONENTS", value, 60)
        }
    }

    val MaxFragmentUniformBlocks by lazy {
        glGetInteger(GL_MAX_FRAGMENT_UNIFORM_BLOCKS).also { value ->
            requireLimit("GL_MAX_FRAGMENT_UNIFORM_BLOCKS", value, 12)
        }
    }

    val MaxFragmentUniformComponents by lazy {
        glGetInteger(GL_MAX_FRAGMENT_UNIFORM_COMPONENTS).also { value ->
            requireLimit("GL_MAX_FRAGMENT_UNIFORM_COMPONENTS", value, 896)
        }
    }

    val MaxFragmentUniformVectors by lazy {
        glGetInteger(GL_MAX_FRAGMENT_UNIFORM_VECTORS).also { value ->
            requireLimit("GL_MAX_FRAGMENT_UNIFORM_VECTORS", value, 224)
        }
    }

    val MaxProgramTexelOffset by lazy {
        glGetInteger(GL_MAX_PROGRAM_TEXEL_OFFSET).also { value ->
            requireLimit("GL_MAX_PROGRAM_TEXEL_OFFSET", value, 7)
        }
    }

    val MaxRenderBufferSize by lazy {
        glGetInteger(GL_MAX_RENDERBUFFER_SIZE).also { value ->
            requireLimit("GL_MAX_RENDERBUFFER_SIZE", value, 2048)
        }
    }

    val MaxSamples by lazy {
        glGetInteger(GL_MAX_SAMPLES).also { value ->
            requireLimit("GL_MAX_SAMPLES", value, 4)
        }
    }

    val MaxServerWaitTimeout by lazy {
        glGetInteger(GL_MAX_SERVER_WAIT_TIMEOUT)
    }

    val MaxTextureImageUnits by lazy {
        glGetInteger(GL_MAX_TEXTURE_IMAGE_UNITS).also { value ->
            requireLimit("GL_MAX_TEXTURE_IMAGE_UNITS", value, 16)
        }
    }

    val MaxTextureLodBias by lazy {
        glGetFloat(GL_MAX_TEXTURE_LOD_BIAS).also { value ->
            requireLimit("GL_MAX_TEXTURE_LOD_BIAS", value, 2.0f)
        }
    }

    val MaxTextureSize by lazy {
        glGetInteger(GL_MAX_TEXTURE_SIZE).also { value ->
            requireLimit("GL_MAX_TEXTURE_SIZE", value, 2048)
        }
    }

    val MaxTransformFeedbackInterleavedComponents by lazy {
        glGetInteger(GL_MAX_TRANSFORM_FEEDBACK_INTERLEAVED_COMPONENTS).also { value ->
            requireLimit("GL_MAX_TRANSFORM_FEEDBACK_INTERLEAVED_COMPONENTS", value, 64)
        }
    }

    val MaxTransformFeedbackSeparateAttribs by lazy {
        glGetInteger(GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_ATTRIBS).also { value ->
            requireLimit("GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_ATTRIBS", value, 4)
        }
    }

    val MaxTransformFeedbackSeparateComponents by lazy {
        glGetInteger(GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_COMPONENTS).also { value ->
            requireLimit("GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_COMPONENTS", value, 4)
        }
    }

    val MaxUniformBlockSize by lazy {
        glGetInteger(GL_MAX_UNIFORM_BLOCK_SIZE).also { value ->
            requireLimit("GL_MAX_UNIFORM_BLOCK_SIZE", value, 16384)
        }
    }

    val MaxUniformBufferBindings by lazy {
        glGetInteger(GL_MAX_UNIFORM_BUFFER_BINDINGS).also { value ->
            requireLimit("GL_MAX_UNIFORM_BUFFER_BINDINGS", value, 24)
        }
    }

    val MaxVaryingComponents by lazy {
        glGetInteger(GL_MAX_VARYING_COMPONENTS).also { value ->
            requireLimit("GL_MAX_VARYING_COMPONENTS", value, 60)
        }
    }

    val MaxVaryingVectors by lazy {
        glGetInteger(GL_MAX_VARYING_VECTORS).also { value ->
            requireLimit("GL_MAX_VARYING_VECTORS", value, 15)
        }
    }

    val MaxVertexAttribs by lazy {
        glGetInteger(GL_MAX_VERTEX_ATTRIBS).also { value ->
            requireLimit("GL_MAX_VERTEX_ATTRIBS", value, 16)
        }
    }

    val MaxVertexTextureImageUnits by lazy {
        glGetInteger(GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS).also { value ->
            requireLimit("GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS", value, 16)
        }
    }

    val MaxVertexOutputComponents by lazy {
        glGetInteger(GL_MAX_VERTEX_OUTPUT_COMPONENTS).also { value ->
            requireLimit("GL_MAX_VERTEX_OUTPUT_COMPONENTS", value, 64)
        }
    }

    val MaxVertexUniformBlocks by lazy {
        glGetInteger(GL_MAX_VERTEX_UNIFORM_BLOCKS).also { value ->
            requireLimit("GL_MAX_VERTEX_UNIFORM_BLOCKS", value, 12)
        }
    }

    val MaxVertexUniformComponents by lazy {
        glGetInteger(GL_MAX_VERTEX_UNIFORM_COMPONENTS).also { value ->
            requireLimit("GL_MAX_VERTEX_UNIFORM_COMPONENTS", value, 1024)
        }
    }

    val MaxVertexUniformVectors by lazy {
        glGetInteger(GL_MAX_VERTEX_UNIFORM_VECTORS).also { value ->
            requireLimit("GL_MAX_VERTEX_UNIFORM_VECTORS", value, 256)
        }
    }

    val MaxViewportDims by lazy {
        glGetInteger(GL_MAX_VIEWPORT_DIMS)
    }

    //TODO:
    /*

        GL_MAX_VERTEX_UNIFORM_VECTORS
        GL_MAX_VIEWPORT_DIMS
     */
}