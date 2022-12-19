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
        //TODO: inject nodespace from somewhere outside of nodeeditor
    }

    override fun render(context: UIContext) {
        context.nodeEditor(
            onLinkCreated = ::createLink,
            onLinkDestroyed = ::destroyLink
        ) {
            Nodespace.current.nodes.filter { it.id >= 0 }.forEach {
                node(it.id, it.name) {
                    it.ports.forEach {
                        when(it.direction) {
                            PortDirection.Input -> input(it.id, it.name)
                            PortDirection.Output -> output(it.id, it.name)
                        }
                    }
                }
            }

            Nodespace.current.links.filter { it.id >= 0 }.forEach {
                link(it.id, it.start.id, it.end.id)
            }
        }
    }

    private fun createLink(startAttribute: Int, endAttribute: Int) {
        val allPorts = Nodespace.current.nodes.flatMap { it.ports }
        val startPort = allPorts.firstOrNull { it.id == startAttribute }
        val endPort = allPorts.firstOrNull { it.id == endAttribute }

        if(startPort != null && endPort != null) {
            if(endPort.type.java.isAssignableFrom(startPort.type.java)) {
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

    private fun destroyLink(linkId: Int) {
        Nodespace.current.links.firstOrNull { it.id == linkId }?.let {
            Nodespace.current -= it
        }
    }

    override fun destroy() {
        super.destroy()
        ImNodes.destroyContext()
    }
}