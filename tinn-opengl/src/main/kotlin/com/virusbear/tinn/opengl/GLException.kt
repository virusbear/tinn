package com.virusbear.tinn.opengl

import com.virusbear.tinn.Driver
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL30C.GL_INVALID_FRAMEBUFFER_OPERATION

class GLException(message: String): Exception(message)

internal fun checkGLErrors(errorFunction: ((Int)->String?)?=null) {
    val error = glGetError()
    if (error != GL_NO_ERROR) {
        val message = when (error) {
            GL_INVALID_OPERATION             -> "GL_INVALID_OPERATION"
            GL_INVALID_VALUE                 -> "GL_INVALID_VALUE"
            GL_INVALID_ENUM                  -> "GL_INVALID_ENUM"
            GL_INVALID_FRAMEBUFFER_OPERATION -> "GL_INVALID_FRAMEBUFFER_OPERATION"
            GL_OUT_OF_MEMORY                 -> "GL_OUT_OF_MEMORY"
            GL_STACK_UNDERFLOW               -> "GL_STACK_UNDERFLOW"
            GL_STACK_OVERFLOW                -> "GL_STACK_OVERFLOW"
            else                             -> "<untranslated: $error>"
        }
        throw GLException("GL ERROR: $message ${errorFunction?.invoke(error) ?: ""}" )
    }
}