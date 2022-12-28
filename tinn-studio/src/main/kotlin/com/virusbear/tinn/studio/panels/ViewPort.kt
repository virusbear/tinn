package com.virusbear.tinn.studio.panels

import com.virusbear.tinn.BaseDestroyable
import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.TextureFilter
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.ui.Panel
import com.virusbear.tinn.ui.UIContext
import imgui.ImVec2
import imgui.flag.ImGuiHoveredFlags
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

        cb?.let {
            //FIXME: for some reason the image displayed is always black. even though during rendering the correct texture is active
            context.image("##viewport", it)
        }
    }

    fun show(colorBuffer: ColorBuffer) {
        colorBuffer.filter(TextureFilter.LINEAR, TextureFilter.LINEAR)
        cb = colorBuffer
    }
}