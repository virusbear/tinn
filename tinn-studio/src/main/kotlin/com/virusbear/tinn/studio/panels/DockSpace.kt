package com.virusbear.tinn.studio.panels

import com.virusbear.tinn.BaseDestroyable
import com.virusbear.tinn.imgui.ImGuiPanel
import com.virusbear.tinn.ui.UIContext
import imgui.ImGui
import imgui.flag.ImGuiDockNodeFlags
import imgui.flag.ImGuiStyleVar
import imgui.flag.ImGuiWindowFlags

class DockSpace: ImGuiPanel, BaseDestroyable() {
    override fun windowFlags(): Int {
        val viewport = ImGui.getMainViewport()
        ImGui.setNextWindowPos(viewport.workPosX, viewport.workPosY)
        ImGui.setNextWindowSize(viewport.workSizeX, viewport.workSizeY)
        ImGui.setNextWindowViewport(viewport.id)
        ImGui.pushStyleVar(ImGuiStyleVar.WindowRounding, 0.0f)
        ImGui.pushStyleVar(ImGuiStyleVar.WindowBorderSize, 0.0f)
        return ImGuiWindowFlags.MenuBar or ImGuiWindowFlags.NoDocking or ImGuiWindowFlags.NoTitleBar or ImGuiWindowFlags.NoCollapse or ImGuiWindowFlags.NoResize or ImGuiWindowFlags.NoMove or ImGuiWindowFlags.NoBringToFrontOnFocus or ImGuiWindowFlags.NoNavFocus
    }

    override val name: String = "##DockSpace"

    override fun render(context: UIContext) {
        ImGui.popStyleVar(2)
        val dockId = ImGui.getID("##DockSpace")
        ImGui.dockSpace(dockId, 0f, 0f, ImGuiDockNodeFlags.None)
    }
}