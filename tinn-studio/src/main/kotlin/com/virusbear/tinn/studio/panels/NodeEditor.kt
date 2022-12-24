package com.virusbear.tinn.studio.panels

import com.virusbear.tinn.BaseDestroyable
import com.virusbear.tinn.EventBus
import com.virusbear.tinn.events.*
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.nodes.Link
import com.virusbear.tinn.nodes.Node
import com.virusbear.tinn.nodes.Nodespace
import com.virusbear.tinn.nodes.PortDirection
import com.virusbear.tinn.ui.Panel
import com.virusbear.tinn.ui.UIContext
import imgui.ImGui
import imgui.ImVec2
import imgui.extension.imnodes.ImNodes
import imgui.extension.imnodes.ImNodesContext
import imgui.extension.imnodes.flag.ImNodesAttributeFlags
import imgui.flag.ImGuiButtonFlags
import imgui.flag.ImGuiHoveredFlags
import imgui.flag.ImGuiKey
import imgui.flag.ImGuiMouseButton

class NodeEditor: Panel, BaseDestroyable() {
    override val name: String = "Node Editor"

    private var nodespace: Nodespace? = null
    //TODO: make list of nodes to support multiselection
    private var selected: Node? = null
    private val nodespaceContext = HashMap<Nodespace, ImNodesContext>()

    init {
        EventBus.subscribe<NodespaceActivateEvent> {
            nodespace = it.nodespace
        }
        EventBus.subscribe<NodeAddedEvent> {
            if(it.nodespace == nodespace) {
                //ImNodes.editorGetPanning().ivec2 wait for this in spair imgui release
                //TODO: Set position of node to center of current nodespace
            }
        }
    }

    override fun init(context: UIContext) {
        ImNodes.createContext()
        ImNodes.pushAttributeFlag(ImNodesAttributeFlags.EnableLinkDetachWithDragClick or ImNodesAttributeFlags.EnableLinkCreationOnSnap)
    }

    override fun render(context: UIContext) {
        nodespace?.let {
            nodespaceContext.computeIfAbsent(it) {
                ImNodes.editorContextCreate()
            }.also {
                ImNodes.editorContextSet(it)
            }
        }

        context.nodeEditor(
            onLinkCreated = onCreateLink(nodespace),
            onLinkDestroyed = onDestroyLink(nodespace)
        ) {
            nodespace?.let {
                it.nodes.filter { it.id >= 0 }.forEach {
                    node(it.id, it.name) {
                        it.ports.forEach {
                            when(it.direction) {
                                PortDirection.Input -> input(it.id, it.name)
                                PortDirection.Output -> output(it.id, it.name)
                            }
                        }
                    }

                    if(ImGui.isItemClicked() && ImGui.isMouseDoubleClicked(ImGuiMouseButton.Left)) {
                        EventBus.publish(NodeEnteredEvent(it))
                    }
                }

                it.links.filter { it.id >= 0 }.forEach {
                    link(it.id, it.start.id, it.end.id)
                }
            }
        }

        val nodeIds = IntArray(ImNodes.numSelectedNodes())
        ImNodes.getSelectedNodes(nodeIds)
        for(id in nodeIds) {
            val node = nodespace?.nodeByIdOrNull(id) ?: continue

            val pos = ImVec2()
            ImNodes.getNodeGridSpacePos(id, pos)
            if(pos.ivec2 != node.position) {
                EventBus.publish(NodeMovedEvent(node, pos.ivec2))
            }
        }

        if(nodeIds.size == 1) {
            nodespace?.let {
                val node = it.nodeByIdOrNull(nodeIds.first())

                if(node != selected) {
                    if(node == null) {
                        EventBus.publish(NodeDeselectEvent)
                    } else {
                        EventBus.publish(NodeSelectEvent(node))
                    }

                    selected = node
                }
            }
        }

        fun updateNodePositions() {
            nodespace?.nodes?.let {
                it.forEach {
                    ImNodes.setNodeGridSpacePos(it.id, it.position.x.toFloat(), it.position.y.toFloat())
                }
            }
        }
        updateNodePositions()

    }

    private fun onCreateLink(nodespace: Nodespace?): (Int, Int) -> Unit = { startAttribute, endAttribute ->
        createLink(nodespace, startAttribute, endAttribute)
    }

    private fun onDestroyLink(nodespace: Nodespace?): (Int) -> Unit = { linkId ->
        destroyLink(nodespace, linkId)
    }

    private fun createLink(nodespace: Nodespace?, startAttribute: Int, endAttribute: Int) {
        if(nodespace == null) return

        val allPorts = nodespace.nodes.flatMap { it.ports }
        val startPort = allPorts.firstOrNull { it.id == startAttribute }
        val endPort = allPorts.firstOrNull { it.id == endAttribute }

        if(startPort != null && endPort != null) {
            if(endPort.type.java.isAssignableFrom(startPort.type.java)) {
                when(startPort.direction) {
                    endPort.direction -> null
                    PortDirection.Input -> Link(endPort, startPort)
                    PortDirection.Output -> Link(startPort, endPort)
                }?.let {
                    nodespace += it
                }
            }
        }
    }

    private fun destroyLink(nodespace: Nodespace?, linkId: Int) {
        if(nodespace == null) return

        nodespace.linkByIdOrNull(linkId)?.let {
            nodespace -= it
        }
    }

    override fun destroy() {
        super.destroy()
        ImNodes.destroyContext()
    }
}

val ImVec2.ivec2: IVec2
get() = IVec2(x.toInt(), y.toInt())