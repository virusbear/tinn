package com.virusbear.tinn.opengl

import com.virusbear.tinn.*
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.window.*
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL30C
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil.NULL

class WindowGL(
    private val native: Long,
    override val multisample: MultiSample
): Window, Trackable() {
    companion object {
        fun create(width: Int, height: Int, title: String, resizable: Boolean, vsync: Boolean, multisample: MultiSample = MultiSample.None): Window {
            glfwDefaultWindowHints()
            glfwWindowHint(GLFW_RESIZABLE, if(resizable) GLFW_TRUE else GLFW_FALSE)
            if(multisample != MultiSample.None) {
                glfwWindowHint(GLFW_SAMPLES, multisample.samples.coerceAtMost(LimitsGL.MaxSamples - 1))
            }

            val window = glfwCreateWindow(width, height, title, NULL, NULL)
            
            if (window == NULL) {
                throw RuntimeException("Failed to create GLFW window")
            }

            val stack = MemoryStack.stackPush()
            try {
                val pWidth = stack.mallocInt(1)
                val pHeight = stack.mallocInt(1)

                glfwGetWindowSize(window, pWidth, pHeight)

                glfwGetVideoMode(glfwGetPrimaryMonitor())?.let { vidmode ->
                    glfwSetWindowPos(
                        window,
                        (vidmode.width() / 2) - pWidth.get(0) / 2,
                        (vidmode.height() / 2) - pHeight.get(0) / 2
                    )
                }
            } finally {
                stack.close()
            }

            glfwMakeContextCurrent(window)
            if(vsync) {
                glfwSwapInterval(1)
            }

            GL.createCapabilities()

            GL30C.glClearColor(0.0f, 0.0f, 0.0f, 0.0f)

            return WindowGL(window, multisample)
        }
    }

    init {
        registerWindowEvents()
    }

    override var position = getWindowPos()
        private set

    override val renderTarget: WindowRenderTarget =
        WindowRenderTargetGL(this)

    override val open: Boolean
        get() = !glfwWindowShouldClose(native)

    override var size: IVec2 = getWindowSize()
        private set

    override var contentScale: Double = getWindowContentScale()
        private set

    override fun clear() {
        GL30C.glClear(GL30C.GL_COLOR_BUFFER_BIT or GL30C.GL_DEPTH_BUFFER_BIT)
    }

    override fun update() {
        glfwSwapBuffers(native)
        glfwPollEvents()
    }

    override fun bind() {
        glfwMakeContextCurrent(native)
    }

    override fun unbind() {
        glfwMakeContextCurrent(0)
    }

    override fun destroy() {
        if(destroyed)
            return

        glfwDestroyWindow(native)

        super.destroy()
    }

    private fun getWindowSize(): IVec2 {
        val width = IntArray(1)
        val height = IntArray(1)
        glfwGetWindowSize(native, width, height)

        return IVec2(width[0], height[0])
    }

    private fun getWindowContentScale(): Double {
        val scale = FloatArray(1)
        val ignored = FloatArray(1)
        glfwGetWindowContentScale(native, scale, ignored)

        return scale[0].toDouble()
    }

    private fun getWindowPos(): IVec2 {
        val x = IntArray(1)
        val y = IntArray(1)
        glfwGetWindowPos(native, x, y)

        return IVec2(x[0], y[0])
    }

    private fun registerWindowEvents() {
        glfwSetWindowSizeCallback(native) { _, width, height ->
            size = IVec2(width, height)
            EventBus.publish(WindowResizeEvent(this, size))
        }
        glfwSetWindowContentScaleCallback(native) { _, scale, _ ->
            contentScale = scale.toDouble()
        }
        glfwSetWindowPosCallback(native) { _, x, y ->
            position = IVec2(x, y)
            EventBus.publish(WindowMoveEvent(this, position))
        }
        glfwSetWindowFocusCallback(native) { _, focused ->
            EventBus.publish(WindowFocusEvent(this, focused))
        }
        glfwSetWindowMaximizeCallback(native) { _, maximized ->
            EventBus.publish(WindowMaximizeEvent(this, maximized))
        }
        glfwSetWindowIconifyCallback(native) { _, minimized ->
            EventBus.publish(WindowMinimizeEvent(this, minimized))
        }

        glfwSetScrollCallback(native) { _, x, y ->
            EventBus.publish(WindowMouseScrollEvent(this, Vec2(x, y)))
        }
        glfwSetCursorEnterCallback(native) { _, entered ->
            if(entered) {
                EventBus.publish(WindowMouseLeaveEvent(this))
            } else {
                EventBus.publish(WindowMouseEnterEvent(this))
            }
        }
        glfwSetCursorPosCallback(native) { _, x, y ->
            EventBus.publish(WindowMouseMoveEvent(this, Vec2(x, y)))
        }
        glfwSetMouseButtonCallback(native) { _, button, action, mods ->
            EventBus.publish(
                WindowMouseButtonEvent(this,
                    MouseButton.fromGl(button),
                    Mod.fromGl(mods),
                    Action.fromGl(action)
                )
            )
        }

        glfwSetCharCallback(native) { _, codepoint ->
            EventBus.publish(WindowCharEvent(this, codepoint.toChar()))
        }

        glfwSetKeyCallback(native) { _, key, code, action, mods ->
            Key.values().firstOrNull { it.code == key }?.let {
                EventBus.publish(
                    WindowKeyEvent( this,
                        it,
                        Action.fromGl(action),
                        Mod.fromGl(mods)
                    )
                )
            }
        }

        glfwSetCharModsCallback(native) { _, codepoint, mods ->
            EventBus.publish(WindowCharModEvent(this, codepoint.toChar(), Mod.fromGl(mods)))
        }
    }
}