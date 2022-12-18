package com.virusbear.tinn.studio.panels

import com.virusbear.tinn.BaseDestroyable
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.ui.Panel
import com.virusbear.tinn.ui.UIContext
import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.Nodespace
import com.virusbear.tinn.nodes.PortDirection
import imgui.ImGui
import imgui.extension.imnodes.ImNodes
import imgui.extension.imnodes.flag.ImNodesAttributeFlags
import imgui.extension.imnodes.flag.ImNodesMiniMapLocation
import imgui.extension.imnodes.flag.ImNodesPinShape
import kotlin.random.Random

class NodeEditor: Panel, BaseDestroyable() {
    override val name: String = "Node Editor"

    override fun init(context: UIContext) {
        ImNodes.createContext()
        ImNodes.pushAttributeFlag(ImNodesAttributeFlags.EnableLinkDetachWithDragClick or ImNodesAttributeFlags.EnableLinkCreationOnSnap)
        Nodespace.push(Nodespace())
    }

    override fun render(context: UIContext) {
        ImNodes.beginNodeEditor()

        Nodespace.current.nodes.forEach {
            ImNodes.beginNode(it.id)

            ImNodes.beginNodeTitleBar()
            ImGui.textUnformatted(it.name)
            ImNodes.endNodeTitleBar()

            it.ports.filter { it.direction == PortDirection.Input }.forEach {
                ImNodes.beginInputAttribute(it.id)
                ImGui.textUnformatted(it.name)
                ImNodes.endInputAttribute()
            }

            it.ports.filter { it.direction == PortDirection.Output }.forEach {
                ImNodes.beginOutputAttribute(it.id)
                ImGui.textUnformatted(it.name)
                ImNodes.endOutputAttribute()
            }

            ImNodes.endNode()
        }

        ImNodes.beginNode(1)
        ImNodes.beginNodeTitleBar()
        ImGui.textUnformatted("Scene")
        ImNodes.endNodeTitleBar()

        ImNodes.beginOutputAttribute(1)
        ImGui.text("output pin")
        ImNodes.endOutputAttribute()

        ImNodes.endNode()

        ImNodes.miniMap(0.2f, ImNodesMiniMapLocation.TopRight)
        ImNodes.endNodeEditor()
    }

    override fun destroy() {
        super.destroy()
        ImNodes.destroyContext()
    }
}