package com.virusbear.tinn.studio.panels

import com.virusbear.tinn.BaseDestroyable
import com.virusbear.tinn.ui.Panel
import com.virusbear.tinn.ui.UIContext
import imgui.ImGui
import imgui.type.ImInt

class Controls: Panel, BaseDestroyable() {
    override val name: String = "Controls"

    private val startFrame = ImInt()
    private val endFrame = ImInt()
    private val currentFrame = IntArray(1)

    override fun render(context: UIContext) {
        //TODO: render buttons
        ImGui.inputInt("##Controls_StartFrame", startFrame)
        ImGui.inputInt("##Controls_EndFrame", endFrame)
        ImGui.sliderInt("##Controls_CurrentFrame", currentFrame, startFrame.get(), endFrame.get())
    }
}