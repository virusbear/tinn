package com.virusbear.tinn.ui.widgets

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.draw.Drawer
import com.virusbear.tinn.extensions.loadImage
import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.math.vec
import java.io.File

class ImageWidget(
    private val image: ColorBuffer
): Widget() {
    override val size: Vec2 = image.size.vec

    override fun measure(constraints: Constraints): Vec2 =
        constraints.maxSize

    override fun draw(drawer: Drawer, bounds: Vec2) {
        drawer.image(image)
    }
}

fun Image(image: ColorBuffer): ImageWidget =
    ImageWidget(image)

fun Image(file: File): ImageWidget =
    Image(loadImage(file))