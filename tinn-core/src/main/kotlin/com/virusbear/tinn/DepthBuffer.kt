package com.virusbear.tinn

interface DepthBuffer: Buffer, Bindable, Destroyable {
    val width: Int
    val height: Int

    //TODO: implement conversion to colorbuffer
}