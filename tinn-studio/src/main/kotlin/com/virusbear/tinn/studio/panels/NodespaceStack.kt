package com.virusbear.tinn.studio.panels

import com.virusbear.tinn.BaseDestroyable
import com.virusbear.tinn.EventBus
import com.virusbear.tinn.events.NodespaceActivateEvent
import com.virusbear.tinn.nodes.Nodespace
import com.virusbear.tinn.studio.NodespacePopEvent
import com.virusbear.tinn.ui.Panel
import com.virusbear.tinn.ui.UIContext
import imgui.ImGui
import imgui.flag.ImGuiMouseButton
import java.util.*

class NodespaceStack: Panel, BaseDestroyable() {
    override val name: String = "Nodespace Stack"

    var activeNodespace: Nodespace? = null
        private set

    init {
        EventBus.subscribe<NodespaceActivateEvent> {
            activeNodespace = it.nodespace
        }

        EventBus.subscribe<NodespacePopEvent> {
            if(activeNodespace != null) {
                EventBus.publish(NodespaceActivateEvent(activeNodespace!!.parent))
            }
        }
    }

    override fun render(context: UIContext) {
        var nodespace: Nodespace? = activeNodespace

        while(nodespace != null) {
            ImGui.selectable("##nodespace_${Random().nextInt()}", nodespace == activeNodespace)
            if(ImGui.isItemClicked() && ImGui.isMouseDoubleClicked(ImGuiMouseButton.Left)) {
                nodespace.makeCurrent()
            }
            ImGui.sameLine()
            ImGui.text(nodespace.name)

            nodespace = nodespace.parent
        }
    }
}