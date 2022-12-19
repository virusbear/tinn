package com.virusbear.tinn

interface VertexBuffer: Buffer, Bindable, Destroyable {
    val size: Int
    val format: VertexFormat

    //TODO: implement reading and writing
}