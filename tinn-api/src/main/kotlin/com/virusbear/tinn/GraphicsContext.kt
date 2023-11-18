package com.virusbear.tinn

interface GraphicsContext {
    val renderThread: RenderThread
    val driver: Driver

    val tracker: Tracker<Destroyable>
}