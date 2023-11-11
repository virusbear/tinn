package com.virusbear.tinn.opengl

import com.virusbear.tinn.*
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.units.dimensions.mm
import com.virusbear.tinn.math.units.dimensions.toInch
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWVidMode

class MonitorGL(
    val native: Long,
    private val context: ContextGL,
    driver: Driver
): Monitor, Trackable(driver) {
    override val size: IVec2
        get() = getVideoMode().run {
            IVec2(width(), height())
        }

    override val position: IVec2
        get() = getMonitorPosition()
    override val contentScale: Double
        get() = getMonitorDensity().contentScale
    override val dpi: Double
        get() = getMonitorDensity().dpi
    override val pixelAspectRatio: Double
        get() = getMonitorDensity().pixelAspectRatio
    override val refreshRate: Int
        get() = getVideoMode().refreshRate()
    override val name: String
        get() = context.glfwGetMonitorName(native) ?: "Unknown"
    override val isPrimary: Boolean
        get() = context.glfwGetPrimaryMonitor() == native

    private fun getVideoMode(): GLFWVidMode =
        context.glfwGetVideoMode(native) ?: error("no VideoMode available for monitor $name")

    private fun getMonitorPosition(): IVec2 =
        context.glfwGetMonitorPos(native)

    private fun getMonitorDensity(): MonitorDensityInformation {
        val (widthMm, heightMm) = context.glfwGetMonitorPhysicalSize(native)
        val videoMode = getVideoMode()

        val widthDpi = videoMode.width() / widthMm.mm.toInch().value
        val heightDpi = videoMode.height() / heightMm.mm.toInch().value
        val pixelAspectRatio = widthDpi / heightDpi

        val (contentScaleX, _) = context.glfwGetMonitorContentScale(native)

        return MonitorDensityInformation(
            pixelAspectRatio = pixelAspectRatio,
            dpi = widthDpi,
            contentScale = contentScaleX
        )
    }

    private data class MonitorDensityInformation(
        val pixelAspectRatio: Double,
        val dpi: Double,
        val contentScale: Double
    )
}