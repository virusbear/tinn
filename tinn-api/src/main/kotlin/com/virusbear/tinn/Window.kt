package com.virusbear.tinn

interface Window: Destroyable, Bindable {
    val native: Long
    val open: Boolean

    fun clear()
    fun update()
}