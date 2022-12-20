package com.virusbear.tinn.studio.panels

import com.virusbear.tinn.BaseDestroyable
import com.virusbear.tinn.EventBus
import com.virusbear.tinn.Program
import com.virusbear.tinn.events.ProgramActivateEvent
import com.virusbear.tinn.ui.Panel
import com.virusbear.tinn.ui.UIContext
import imgui.ImGui
import imgui.type.ImInt

class Controls: Panel, BaseDestroyable() {
    override val name: String = "Controls"

    private val startFrame = ImInt()
    private val endFrame = ImInt()
    private val currentFrame = IntArray(1)

    private var program: Program? = null

    init {
        EventBus.subscribe<ProgramActivateEvent> {
            program = it.program
        }
    }

    override fun render(context: UIContext) {
        if(ImGui.button("Step")) {
            program?.step()
        }; ImGui.sameLine()
        //TODO: make imagebutton
        if(ImGui.button("Reset")) {
            program?.reset()
        }; ImGui.sameLine()
        if(ImGui.button("Start")) {
            program?.start()
        }; ImGui.sameLine()
        if(ImGui.button("Pause")) {
            program?.stop()
            program?.reset()
        }; ImGui.sameLine()
        if(ImGui.button("Stop")) {
            program?.stop()
        }
        ImGui.inputInt("##Controls_StartFrame", startFrame)
        ImGui.inputInt("##Controls_EndFrame", endFrame)
        ImGui.sliderInt("##Controls_CurrentFrame", currentFrame, startFrame.get(), endFrame.get())
    }
}