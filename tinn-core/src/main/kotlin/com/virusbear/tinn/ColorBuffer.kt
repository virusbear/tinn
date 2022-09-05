package com.virusbear.tinn

interface ColorBuffer: Bindable, Destroyable {
    val width: Int
    val height: Int

    //TODO: implement reading and writing
}