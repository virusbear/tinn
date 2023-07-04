package com.virusbear.tinn.studio

import com.virusbear.tinn.*
import com.virusbear.tinn.ui.Panel
import com.virusbear.tinn.ui.UIContext
import imgui.ImGui
import java.io.File

class MainMenuBar(val program: Program): Panel, BaseDestroyable() {
    override val name: String = "Main Menu"

    override fun render(context: UIContext) {
        if(ImGui.beginMainMenuBar()) {
            if(ImGui.beginMenu("File")) {
                if(ImGui.menuItem("Open", "Ctrl + O")) {
                    val reader = NbtSceneReader(File("C:\\Temp\\TEst.scn"))
                    program.load(reader, EmptyContext)
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