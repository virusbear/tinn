package com.virusbear.tinn.window

import com.virusbear.tinn.Bindable
import com.virusbear.tinn.Destroyable
import com.virusbear.tinn.Monitor
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
    val monitor: Monitor

    val dpi: Double
        get() = monitor.dpi
    val pixelAspectRatio: Double
        get() = monitor.pixelAspectRatio
    val refreshRate: Int
        get() = monitor.refreshRate

    val renderTarget: WindowRenderTarget

    fun clear()
    fun update()
}