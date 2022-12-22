package com.virusbear.tinn.studio.panels

import com.virusbear.tinn.BaseDestroyable
import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.ui.Panel
import com.virusbear.tinn.ui.UIContext
import imgui.internal.ImGui

object ViewPort: Panel, BaseDestroyable() {
    override val name: String = "Viewport"

    private var cb: ColorBuffer? = null

    var size: IVec2 = IVec2.ZERO
        private set

    override fun render(context: UIContext) {
        if(cb == null) return

        if(size.x != ImGui.getContentRegionAvail().x.toInt() || size.y != ImGui.getContentRegionAvail().y.toInt()) {
            size = IVec2(ImGui.getContentRegionAvail().x.toInt().coerceAtLeast(0), ImGui.getContentRegionAvail().y.toInt().coerceAtLeast(0))
        }

        cb?.let { context.image("##viewport", it) }
    }

    fun show(colorBuffer: ColorBuffer) {
        cb = colorBuffer
    }
}