package com.virusbear.tinn

interface Destroyable {
    val destroyed: Boolean
    fun destroy()
}