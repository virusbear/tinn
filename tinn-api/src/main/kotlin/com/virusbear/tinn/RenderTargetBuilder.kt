package com.virusbear.tinn

import com.virusbear.tinn.extensions.colorBuffer as _colorBuffer

class RenderTargetBuilder(private val renderTarget: RenderTarget) {
    fun colorBuffer(format: ColorFormat = ColorFormat.RGBA8, multisample: MultiSample = MultiSample.None) {
        val cb = _colorBuffer(renderTarget.width, renderTarget.height, renderTarget.contentScale, multisample, format)
        renderTarget.attach(cb, 0)
    }
}