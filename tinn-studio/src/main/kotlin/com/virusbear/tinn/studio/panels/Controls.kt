package com.virusbear.tinn.studio.panels

import com.virusbear.tinn.BaseDestroyable
import com.virusbear.tinn.EventBus
import com.virusbear.tinn.events.ProgramControlEvent
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
        if(ImGui.button("Step")) {
            EventBus.publish(ProgramControlEvent.Step)
        }; ImGui.sameLine()
        //TODO: make imagebutton
        if(ImGui.button("Reset")) {
            EventBus.publish(ProgramControlEvent.Reset)
        }; ImGui.sameLine()
        if(ImGui.button("Start")) {
            EventBus.publish(ProgramControlEvent.Start)
        }; ImGui.sameLine()
        if(ImGui.button("Pause")) {
            EventBus.publish(ProgramControlEvent.Pause)
        }; ImGui.sameLine()
        if(ImGui.button("Stop")) {
            EventBus.publish(ProgramControlEvent.Stop)
        }
        ImGui.inputInt("##Controls_StartFrame", startFrame)
        ImGui.inputInt("##Controls_EndFrame", endFrame)
        ImGui.sliderInt("##Controls_CurrentFrame", currentFrame, startFrame.get(), endFrame.get())
    }
}