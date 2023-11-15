package com.virusbear.tinn.opengl

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.Driver
import com.virusbear.tinn.RenderTarget
import com.virusbear.tinn.Trackable
import com.virusbear.tinn.draw.Drawer
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.window.Window
import com.virusbear.tinn.window.WindowRenderTarget
import org.lwjgl.opengl.GL30C.*
import java.util.*

class WindowRenderTargetGL(override val window: Window, context: GraphicsContextGL, driver: Driver):
    RenderTargetGL(
        0, 0, 1.0,
        glGetInteger(GL_FRAMEBUFFER_BINDING),
        context,
        driver
    ), WindowRenderTarget {

    override val width: Int
        get() = window.width
    override val height: Int
        get() = window.height
    override val contentScale: Double
        get() = window.contentScale

    override fun toString(): String {
        return "WindowRenderTargetGL($width x $height * $contentScale, $frameBuffer)"
    }
}

open class RenderTargetGL(
    override val width: Int,
    override val height: Int,
    override val contentScale: Double,
    protected val frameBuffer: Int = glGenFramebuffers(),
    private val context: GraphicsContextGL,
    driver: Driver
) : RenderTarget, Trackable(driver) {
    //TODO: separate type for ColorBufferAttachment necessary?
    private val colorAttachments = mutableListOf<ColorBuffer>()

    init {
        checkGLErrors()
    }

    companion object {
        private val active = mutableMapOf<Long, Stack<RenderTarget>>()

        val activeRenderTarget: RenderTarget
            get() {
                val stack = active.getOrPut((Driver.driver as DriverGL).currentContext.native) { Stack() }
                return stack.peek()
            }
    }

    override val drawer: Drawer by lazy {
        driver.createDrawer(context)
    }

    override fun bind() {
        require(!destroyed)

        val stack = active.getOrPut(context.native) { Stack() }
        stack.push(this)
        bindTarget()
    }

    override fun unbind() {
        val previous = active.getOrPut(context.native) { Stack() }.let {
            if(it.peek() !== this) {
                null
            } else {
                it.pop()
                if(it.isEmpty()) {
                    null
                } else {
                    it.pop()
                }
            }
        }

        previous?.bind()
    }

    override fun attach(colorBuffer: ColorBuffer, level: Int) {
        require(!destroyed)
        require(colorBuffer is ColorBufferGL)

        val div = 1 shl level
        require(colorBuffer.effectiveWidth / div == effectiveWidth && colorBuffer.effectiveHeight / div == effectiveHeight) {
            "buffer dimension mismatch."
        }

        bound {
            context.glFramebufferTexture2D(
                GL_FRAMEBUFFER,
                GL_COLOR_ATTACHMENT0 + colorAttachments.size,
                colorBuffer.target,
                colorBuffer.textureId,
                level
            )

            context.checkGLErrors()

            colorAttachments += colorBuffer
        }
    }

    override fun colorBuffer(index: Int): ColorBuffer =
        colorAttachments[index]

    override fun destroy() {
        super.destroy()

        if(this is WindowRenderTargetGL) {
            return
        }
        
        context.glDeleteFramebuffers(frameBuffer)
        context.checkGLErrors()
    }

    private fun bindTarget() {
        context.glBindFramebuffer(GL_FRAMEBUFFER, frameBuffer)

        if(colorAttachments.isNotEmpty()) {
            val attachments = List(colorAttachments.size) { idx -> GL_COLOR_ATTACHMENT0 + idx }.toIntArray()
            context.glDrawBuffers(attachments)
            context.checkGLErrors {
                when(it) {
                    GL_INVALID_ENUM -> "1. one of the values in bufs is not an accepted value\n2. the API call refers to the default framebuffer and one or more of the values in bufs is one of the GL_COLOR_ATTACHMENTn tokens\n3. the API call refers to a framebuffer object and one or more of the values in bufs is anything other than GL_NONE or one of the GL_COLOR_ATTACHMENTn tokens\n4. n is less than 0"
                    GL_INVALID_OPERATION -> "a symbolic constant other than GL_NONE appears more than once in bufs."
                    GL_INVALID_VALUE -> "1. n is greater than GL_MAX_DRAW_BUFFERS\n 2. any of the entries in bufs (other than GL_NONE ) indicates a color buffer that does not exist in the current GL context\n 3. any value in bufs is GL_BACK, and n is not one"
                    else -> null
                }
            }
        }


        context.glViewport(IVec2.ZERO, IVec2(width, height))
    }
}