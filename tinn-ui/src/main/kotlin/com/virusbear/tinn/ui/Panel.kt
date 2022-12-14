package com.virusbear.tinn.ui

import com.virusbear.tinn.Destroyable

interface Panel: Destroyable {
    val name: String

    fun render(context: UIContext)
    fun init(context: UIContext) {}
}