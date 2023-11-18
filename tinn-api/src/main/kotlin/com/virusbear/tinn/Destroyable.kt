package com.virusbear.tinn

interface Destroyable {
    val destroyed: Boolean
    fun destroy()
}

fun Destroyable.requireAlive(lazyMessage: () -> Any = { }) {
    check(!destroyed, lazyMessage)
}