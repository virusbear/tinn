package com.virusbear.tinn

import com.virusbear.tinn.math.IVec2

interface Window: Destroyable, Bindable {
    val native: Long
    val open: Boolean

    val width: Int
        get() = size.x
    val height: Int
        get() = size.y
    val size: IVec2
    val contentScale: Double
    val multisample: MultiSample

    val renderTarget: WindowRenderTarget

    fun clear()
    fun update()
}