package com.virusbear.tinn

interface DepthBuffer: Bindable, Destroyable {
    val width: Int
    val height: Int

    //TODO: implement conversion to colorbuffer
}