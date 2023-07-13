package com.virusbear.tinn

import com.virusbear.tinn.draw.Drawer
import com.virusbear.tinn.math.IVec2

interface RenderTarget: Destroyable, Bindable {
    val width: Int
    val height: Int
    val size: IVec2
        get() = IVec2(width, height)

    val contentScale: Double

    val drawer: Drawer

    val effectiveWidth: Int
        get() = (width * contentScale).toInt()
    val effectiveHeight: Int
        get() = (height * contentScale).toInt()
    val effectiveSize: IVec2
        get() = IVec2(effectiveWidth, effectiveHeight)

    fun attach(colorBuffer: ColorBuffer, level: Int = 0)

    fun colorBuffer(index: Int): ColorBuffer

    companion object {
        val active: RenderTarget
            get() = Driver.driver.activeRenderTarget
    }
}