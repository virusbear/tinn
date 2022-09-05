package com.virusbear.tinn.ui

interface Panel {
    val name: String
    fun render(context: UIContext)
}