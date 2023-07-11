package com.virusbear.tinn

import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.colorBuffer as _colorBuffer

interface RenderTarget: Destroyable, Bindable {
    val width: Int
    val height: Int
    val size: IVec2
        get() = IVec2(width, height)

    val contentScale: Double

    val effectiveWidth: Int
        get() = (width * contentScale).toInt()
    val effectiveHeight: Int
        get() = (height * contentScale).toInt()
    val effectiveSize: IVec2
        get() = IVec2(effectiveWidth, effectiveHeight)

    fun attach(colorBuffer: ColorBuffer, level: Int = 0)

    companion object {
        val active: RenderTarget
            get() = Driver.driver.activeRenderTarget
    }
}

class RenderTargetBuilder(private val renderTarget: RenderTarget) {
    fun colorBuffer(format: ColorFormat = ColorFormat.RGBA8, multisample: MultiSample = MultiSample.None) {
        val cb = _colorBuffer(renderTarget.width, renderTarget.height, renderTarget.contentScale, multisample, format)
        renderTarget.attach(cb, 0)
    }
}

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

