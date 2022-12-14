package com.virusbear.tinn.studio.panels

import com.virusbear.tinn.*
import com.virusbear.tinn.ui.Panel
import com.virusbear.tinn.ui.UIContext
import imgui.internal.ImGui

class ViewPort: Panel, BaseDestroyable() {
    override val name: String = "Viewport"

    private var cb: ColorBuffer? = null

    override fun render(context: UIContext) {
        if(cb == null) {
            cb = Driver.driver.createColorBuffer(ImGui.getContentRegionAvail().x.toInt(), ImGui.getContentRegionAvail().y.toInt(), ColorFormat.RGBA8, MultiSample.Count(8u), MipMapLevel.None)
        }

        var viewPort: ColorBuffer = cb ?: return

        if(viewPort.width != ImGui.getContentRegionAvail().x.toInt() || viewPort.height != ImGui.getContentRegionAvail().y.toInt()) {
            println("Creating new buffer: OldSize: (${viewPort.width}, ${viewPort.height}). NewSize: (${ImGui.getContentRegionAvail().x.toInt()}, ${ImGui.getContentRegionAvail().y.toInt()})")
            viewPort.destroy()
            viewPort = Driver.driver.createColorBuffer(ImGui.getContentRegionAvail().x.toInt(), ImGui.getContentRegionAvail().y.toInt(), ColorFormat.RGBA8, MultiSample.Count(8u), MipMapLevel.None)
        }

        context.image(viewPort)
    }
}