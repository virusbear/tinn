package com.virusbear.tinn.opengl

import com.virusbear.tinn.*
import com.virusbear.tinn.color.Color
import com.virusbear.tinn.math.*
import com.virusbear.tinn.window.*
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL30C
import org.lwjgl.system.MemoryUtil.NULL

class WindowGL(
    private val native: Long,
    override val multisample: MultiSample,
    private val context: ContextGL,
    driver: Driver
): Window, Trackable(driver) {
    companion object {
        fun create(width: Int, height: Int, title: String, resizable: Boolean, vsync: Boolean, multisample: MultiSample = MultiSample.None, driver: Driver): Window {
            val context = ContextGL(driver)

            context.glfwDefaultWindowHints()
            context.glfwWindowHint(GLFW_DOUBLEBUFFER, GLFW_TRUE)
            context.glfwWindowHint(GLFW_RESIZABLE, if(resizable) GLFW_TRUE else GLFW_FALSE)
            if(multisample != MultiSample.None) {
                context.glfwWindowHint(GLFW_SAMPLES, multisample.samples.coerceAtMost(LimitsGL.MaxSamples - 1))
            }

            val window = context.glfwCreateWindow(width, height, title, NULL, NULL)

            if (window == NULL) {
                throw RuntimeException("Failed to create GLFW window")
            }

            val windowSize = context.glfwGetWindowSize(window)

            Driver.driver.availableMonitors.firstOrNull()?.size?.let { size ->
                context.glfwSetWindowPos(window,(size / 2) - (windowSize / 2))
            }

            context.makeCurrent(window)
            if(vsync) {
                context.glfwSwapInterval(1)
            }

            context.createCapabilities()

            context.glClearColor(Color.TRANSPARENT)

            return WindowGL(window, multisample, context, driver)
        }
    }

    init {
        bind()
        registerWindowEvents()
    }

    //IMPORTANT: getWindowPos returns the position in virtual screen space spanning multiple monitors
    override var position = getWindowPos()
        private set

    override val renderTarget: WindowRenderTarget =
        WindowRenderTargetGL(this, context, driver)

    override val open: Boolean
        get() = !context.glfwWindowShouldClose(native)

    override var size: IVec2 = getWindowSize()
        private set

    override var contentScale: Double = getWindowContentScale()
        private set

    override val monitor: Monitor
        get() = getWindowMonitor()

    override fun clear() {
        context.glClear(GL30C.GL_COLOR_BUFFER_BIT or GL30C.GL_DEPTH_BUFFER_BIT)
    }

    override fun update() {
        context.glfwSwapBuffers(native)
    }

    fun pollEvents() {
        context.glfwPollEvents()
    }

    override fun bind() {
        context.makeCurrent(native)
    }

    override fun unbind() {
        context.clearCurrent()
    }

    override fun destroy() {
        if(destroyed)
            return

        unbind()
        context.glfwDestroyWindow(native)
        context.destroy()

        super.destroy()
    }

    private fun getWindowSize(): IVec2 =
        context.glfwGetWindowSize(native)

    private fun getWindowContentScale(): Double =
        context.glfwGetWindowContentScale(native).x

    private fun getWindowMonitor(): Monitor {
        context.glfwGetWindowMonitor(native).let {
            if(it != NULL) {
                return MonitorGL(native)
            }
        }

        return getClosestMonitor()
    }

    private fun getClosestMonitor(): Monitor {
        val monitors = driver.availableMonitors
        val windowPos = position
        val windowSize = size
        val windowArea = Rect(windowPos.vec, (windowPos + windowSize).vec)

        return monitors.map { monitor ->
            val monitorArea = Rect(monitor.position.vec, (monitor.position + monitor.size).vec)
            val intersection = windowArea intersect monitorArea
            monitor to (intersection?.area ?: Double.NEGATIVE_INFINITY)
        }.maxBy { it.second }.first
    }

    private fun getWindowPos(): IVec2 =
        context.glfwGetWindowPos(native)

    private fun registerWindowEvents() {
        context.glfwSetWindowSizeCallback(native) { size ->
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