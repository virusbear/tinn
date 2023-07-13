package com.virusbear.tinn.extensions

import com.virusbear.tinn.Driver
import com.virusbear.tinn.RenderTarget
import com.virusbear.tinn.RenderTargetBuilder
import com.virusbear.tinn.draw.Drawer

fun renderTarget(
    width: Int,
    height: Int,
    contentScale: Double = 1.0,
    builder: RenderTargetBuilder.() -> Unit
): RenderTarget {
    require(width > 0 && height > 0) { "unsupported resolution (${width}x$height)" }

    val target = Driver.driver.createRenderTarget(width, height, contentScale)
    RenderTargetBuilder(target).builder()
    return target
}

fun RenderTarget.draw(
    block: Drawer.() -> Unit
) {
    bound {
        drawer.begin(width, height, contentScale)
        drawer.block()
        drawer.end()
    }
}