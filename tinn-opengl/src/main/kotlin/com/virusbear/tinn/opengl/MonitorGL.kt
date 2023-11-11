package com.virusbear.tinn.opengl

import com.virusbear.tinn.*
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.units.dimensions.mm
import com.virusbear.tinn.math.units.dimensions.toInch
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWVidMode

class MonitorGL(
    val native: Long
): Monitor, Trackable() {
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
        get() = glfwGetMonitorName(native) ?: "Unknown"
    override val isPrimary: Boolean
        get() = glfwGetPrimaryMonitor() == native

    private fun getVideoMode(): GLFWVidMode =
        glfwGetVideoMode(native) ?: error("no VideoMode available for monitor $name")

    private fun getMonitorPosition(): IVec2 {
        val x = IntArray(1)
        val y = IntArray(1)
        glfwGetMonitorPos(native, x, y)

        return IVec2(x[0], y[0])
    }

    private fun getMonitorDensity(): MonitorDensityInformation {
        val widthMm = IntArray(1)
        val heightMm = IntArray(1)
        glfwGetMonitorPhysicalSize(native, widthMm, heightMm)
        val videoMode = getVideoMode()

        val widthDpi = videoMode.width() / widthMm[0].mm.toInch().value
        val heightDpi = videoMode.height() / heightMm[0].mm.toInch().value
        val pixelAspectRatio = widthDpi / heightDpi

        val contentScaleX = FloatArray(1)
        val contentScaleY = FloatArray(1)
        glfwGetMonitorContentScale(native, contentScaleX, contentScaleY)

        return MonitorDensityInformation(
            pixelAspectRatio = pixelAspectRatio,
            dpi = widthDpi,
            contentScale = contentScaleX[0].toDouble()
        )
    }

    private data class MonitorDensityInformation(
        val pixelAspectRatio: Double,
        val dpi: Double,
        val contentScale: Double
    )
}