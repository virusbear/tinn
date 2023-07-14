package com.virusbear.tinn.window

import com.virusbear.tinn.RenderTarget

interface WindowRenderTarget: RenderTarget {
    val window: Window
}