package com.virusbear.tinn.window

import com.virusbear.tinn.Bindable
import com.virusbear.tinn.Destroyable
import com.virusbear.tinn.MultiSample
import com.virusbear.tinn.math.IVec2

interface Window: Destroyable, Bindable {
    val open: Boolean

    val width: Int
        get() = size.x
    val height: Int
        get() = size.y
    val size: IVec2
    val position: IVec2
    val contentScale: Double
    val multisample: MultiSample

    val renderTarget: WindowRenderTarget

    fun clear()
    fun update()
}