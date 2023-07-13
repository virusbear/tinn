package com.virusbear.tinn.studio

import com.virusbear.tinn.*
import com.virusbear.tinn.color.Color
import com.virusbear.tinn.draw.LineCap
import com.virusbear.tinn.extensions.draw
import com.virusbear.tinn.extensions.isolated
import com.virusbear.tinn.extensions.loadImage
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
import java.io.File

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

    val image = loadImage(File("H:\\git\\tinn\\prusa.png"))
    image.filter()

image.generateMipMaps()
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
                image(image)
            }

            rectangle(window.size.vec, Vec2.ONE * 50.0)

            fill = Color.BLACK
            point(window.size.vec / 2.0)

            font = loadFont("JetBrainsMono-Regular", "H:\\git\\tinn\\JetBrainsMono-Regular.ttf")
            fontSize = 14.0
            val textSize = measureText("Hello World!")
            val textPos = window.size.vec / 2.0 - Vec2(textSize.x, -textSize.y / 2.0) / 2.0
            stroke = Color.BLACK
            line(textPos, textPos + textSize.copy(y = 0.0))
            text(textPos, "Hello World!")
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