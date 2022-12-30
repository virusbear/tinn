package com.virusbear.tinn.studio

import com.virusbear.tinn.BaseDestroyable
import com.virusbear.tinn.ui.Panel
import com.virusbear.tinn.ui.UIContext
import imgui.ImGui
import imgui.extension.imguifiledialog.ImGuiFileDialog
import imgui.extension.imguifiledialog.flag.ImGuiFileDialogFlags

class MainMenuBar: Panel, BaseDestroyable() {
    override val name: String = "Main Menu"

    override fun render(context: UIContext) {
        if(ImGui.beginMainMenuBar()) {
            if(ImGui.beginMenu("File")) {
                if(ImGui.menuItem("Open", "Ctrl + O")) {
                    ImGuiFileDialog.openDialog("OpenFile", "Open Scene", "*.scn", "", "", 1, 0L, ImGuiFileDialogFlags.None)
                }


                ImGui.endMenu()
            }

            ImGui.endMainMenuBar()
        }
    }
}