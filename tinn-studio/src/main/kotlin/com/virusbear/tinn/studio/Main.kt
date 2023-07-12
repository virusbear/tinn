package com.virusbear.tinn.studio

import com.virusbear.tinn.*
import com.virusbear.tinn.color.Color
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.math.rad
import com.virusbear.tinn.math.vec
import com.virusbear.tinn.opengl.DriverGL
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

    var mousePos = Vec2.ZERO

    GLFW.glfwSetCursorPosCallback(window.native) { _, x, y ->
        mousePos = Vec2(x, y)
    }

    var framecount = 0

    loop(window) {
        framecount++
        window.renderTarget.draw {
            noStroke()
            fill = Color(0.5, 0.5, 0.5, 1.0)
            circle(Vec2.ZERO, window.size.x / 4.0)
            isolated {
                fill = Color(1.0, 1.0, 1.0, 1.0)
                translate(window.size.vec / 2.0)
                rotate((framecount.toDouble() / 60.0).rad)
                translate(Vec2.ONE * -50)
                rectangle(Vec2.ZERO, Vec2.ONE * 100.0)
            }

            rectangle(window.size.vec, Vec2.ONE * 50.0)

            fill = Color.BLACK
            point(window.size.vec / 2.0)
        }
        /*window.renderTarget.bound {
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
        }*/
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