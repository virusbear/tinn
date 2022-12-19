package com.virusbear.tinn

interface RenderTarget: Bindable, Destroyable {
    val width: Int
    val height: Int
}