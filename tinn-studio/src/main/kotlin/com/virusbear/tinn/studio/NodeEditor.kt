package com.virusbear.tinn.studio

import com.virusbear.tinn.BaseDestroyable
import com.virusbear.tinn.ui.Panel
import com.virusbear.tinn.ui.UIContext
import imgui.extension.imnodes.ImNodes
import imgui.extension.imnodes.flag.ImNodesMiniMapLocation

class NodeEditor: Panel, BaseDestroyable() {
    override val name: String = "Node Editor"

    override fun init(context: UIContext) {
        ImNodes.createContext()
    }

    override fun render(context: UIContext) {
        ImNodes.beginNodeEditor()

        ImNodes.miniMap(0.2f, ImNodesMiniMapLocation.TopRight)
        ImNodes.endNodeEditor()
    }

    override fun destroy() {
        super.destroy()
        ImNodes.destroyContext()
    }
}