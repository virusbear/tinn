package com.virusbear.tinn.studio

import com.virusbear.tinn.BaseDestroyable
import com.virusbear.tinn.NbtSceneWriter
import com.virusbear.tinn.Program
import com.virusbear.tinn.ui.Panel
import com.virusbear.tinn.ui.UIContext
import imgui.ImGui
import imgui.extension.imguifiledialog.ImGuiFileDialog
import imgui.extension.imguifiledialog.flag.ImGuiFileDialogFlags
import java.io.File

class MainMenuBar(val program: Program): Panel, BaseDestroyable() {
    override val name: String = "Main Menu"

    override fun render(context: UIContext) {
        if(ImGui.beginMainMenuBar()) {
            if(ImGui.beginMenu("File")) {
                if(ImGui.menuItem("Open", "Ctrl + O")) {
                    println("Menu open")
                }
                if(ImGui.menuItem("Save", "Ctrl + S")) {
                    val writer = NbtSceneWriter()
                    program.save(writer)

                    writer.saveAs(File("C:\\Temp\\TEst.scn"))
                }

                ImGui.endMenu()
            }

            ImGui.endMainMenuBar()
        }
    }
}