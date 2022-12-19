package com.virusbear.tinn.studio.panels

import com.virusbear.tinn.BaseDestroyable
import com.virusbear.tinn.nodes.Link
import com.virusbear.tinn.ui.Panel
import com.virusbear.tinn.ui.UIContext
import com.virusbear.tinn.nodes.Nodespace
import com.virusbear.tinn.nodes.PortDirection
import imgui.ImGui
import imgui.extension.imnodes.ImNodes
import imgui.extension.imnodes.flag.ImNodesAttributeFlags
import imgui.extension.imnodes.flag.ImNodesMiniMapLocation
import imgui.type.ImBoolean
import imgui.type.ImInt

class NodeEditor: Panel, BaseDestroyable() {
    override val name: String = "Node Editor"

    override fun init(context: UIContext) {
        ImNodes.createContext()
        ImNodes.pushAttributeFlag(ImNodesAttributeFlags.EnableLinkDetachWithDragClick or ImNodesAttributeFlags.EnableLinkCreationOnSnap)
        Nodespace.push(Nodespace())
    }

    override fun render(context: UIContext) {
        ImNodes.beginNodeEditor()

        Nodespace.current.nodes.filter { it.id >= 0 }.forEach {
            ImNodes.beginNode(it.id)

            ImNodes.beginNodeTitleBar()
            ImGui.textUnformatted(it.name)
            ImNodes.endNodeTitleBar()

            it.ports.filter { it.id >= 0 }.filter { it.direction == PortDirection.Input }.forEach {
                ImNodes.beginInputAttribute(it.id)
                ImGui.textUnformatted(it.name)
                ImNodes.endInputAttribute()
            }

            it.ports.filter { it.id >= 0 }.filter { it.direction == PortDirection.Output }.forEach {
                ImNodes.beginOutputAttribute(it.id)
                ImGui.textUnformatted(it.name)
                ImNodes.endOutputAttribute()
            }

            ImNodes.endNode()
        }

        Nodespace.current.links.filter { it.id >= 0 }.forEach {
            ImNodes.link(it.id, it.start.id, it.end.id)
        }

        ImNodes.miniMap(0.2f, ImNodesMiniMapLocation.TopRight)
        ImNodes.endNodeEditor()

        val startNodeId = ImInt()
        val startPortId = ImInt()
        val endNodeId = ImInt()
        val endPortId = ImInt()
        if(ImNodes.isLinkCreated(startNodeId, startPortId, endNodeId, endPortId, ImBoolean())) {
            val startNode = Nodespace.current.nodes.firstOrNull { it.id == startNodeId.get() }
            val endNode = Nodespace.current.nodes.firstOrNull { it.id == endNodeId.get() }

            val startPort = startNode?.ports?.firstOrNull { it.id == startPortId.get() }
            val endPort = endNode?.ports?.firstOrNull { it.id == endPortId.get() }

            if(startPort != null && endPort != null) {
                if(startPort.type == endPort.type) {
                    when(startPort.direction) {
                        endPort.direction -> null
                        PortDirection.Input -> Link(endPort, startPort)
                        PortDirection.Output -> Link(startPort, endPort)
                    }?.let {
                        Nodespace.current += it
                    }
                }
            }
        }

        val linkId = ImInt()
        if(ImNodes.isLinkDestroyed(linkId)) {
            Nodespace.current.links.firstOrNull { it.id == linkId.get() }?.let {
                Nodespace.current -= it
            }
        }
    }

    override fun destroy() {
        super.destroy()
        ImNodes.destroyContext()
    }
}