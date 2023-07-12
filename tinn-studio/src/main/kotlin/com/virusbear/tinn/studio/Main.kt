package com.virusbear.tinn.studio

import com.virusbear.tinn.Driver
import com.virusbear.tinn.MultiSample
import com.virusbear.tinn.Window
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.opengl.DriverGL
import com.virusbear.tinn.renderTarget
import org.lwjgl.glfw.GLFW
import org.lwjgl.nanovg.NVGColor
import org.lwjgl.nanovg.NVGPaint
import org.lwjgl.nanovg.NanoVG
import org.lwjgl.nanovg.NanoVGGL3
import org.lwjgl.opengl.GL11C.glViewport

fun main() {
    Driver.use(DriverGL())

    val window: Window = Driver.use {
        init()
        createWindow(800, 600, "tinn", resizable = true, vsync = true, multisample = MultiSample.None)
    }

    val ctx = NanoVGGL3.nvgCreate(NanoVGGL3.NVG_ANTIALIAS or NanoVGGL3.NVG_STENCIL_STROKES)
println(ctx)
    val fill = NVGColor.create()

    var mousePos = Vec2.ZERO

    GLFW.glfwSetCursorPosCallback(window.native) { _, x, y ->
        mousePos = Vec2(x, y)
    }

    val renderTarget = renderTarget(400, 400) {
        colorBuffer()
    }

    loop(window) {
        window.renderTarget.bound {
            NanoVG.nvgBeginFrame(ctx, window.width.toFloat(), window.height.toFloat(), 1f)

            NanoVG.nvgBeginPath(ctx)
            NanoVG.nvgCircle(ctx, mousePos.x.toFloat() / 2.0f, (window.height / window.contentScale).toFloat() + mousePos.y.toFloat() / 2.0f, 120f)
            fill.r(0.5f)
            fill.g(0.5f)
            fill.b(0.5f)
            fill.a(1.0f)
            NanoVG.nvgFillColor(ctx, fill)
            NanoVG.nvgFill(ctx)

            NanoVG.nvgCircle(ctx, mousePos.y.toFloat() / 2.0f, (window.height / window.contentScale).toFloat() + mousePos.y.toFloat() / 2.0f, 120f)
            fill.r(0.5f)
            fill.g(0.5f)
            fill.b(0.5f)
            fill.a(1.0f)
            NanoVG.nvgFillColor(ctx, fill)
            NanoVG.nvgFill(ctx)

            NanoVG.nvgEndFrame(ctx)
        }
    }

    Driver.driver.destroy()
}

private fun loop(window: Window, block: () -> Unit) {
    window.bound {
        while (window.open) {
            window.clear()

            block()

            window.update()
        }
    }
}