package com.virusbear.tinn.studio.panels

import com.virusbear.tinn.BaseDestroyable
import com.virusbear.tinn.EventBus
import com.virusbear.tinn.events.NodespaceActivateEvent
import com.virusbear.tinn.nodes.Nodespace
import com.virusbear.tinn.ui.Panel
import com.virusbear.tinn.ui.UIContext
import imgui.ImGui
import imgui.flag.ImGuiMouseButton
import java.util.*

class NodespaceStack: Panel, BaseDestroyable() {
    override val name: String = "Nodespace Stack"

    val nodespaceStack = Stack<Nodespace>()

    init {
        EventBus.subscribe<NodespaceActivateEvent> {
            if(it.nodespace == null) {
                nodespaceStack.clear()
                return@subscribe
            }

            if(it.nodespace in nodespaceStack) {
                while(nodespaceStack.peek() != it.nodespace) {
                    nodespaceStack.pop()
                }
            } else {
                nodespaceStack.push(it.nodespace)
            }
        }
    }

    override fun render(context: UIContext) {
        nodespaceStack.toList().forEach {
            ImGui.selectable("##nodespace_${Random().nextInt()}", it == nodespaceStack.peek())
            if(ImGui.isItemClicked() && ImGui.isMouseDoubleClicked(ImGuiMouseButton.Left)) {
                it.makeCurrent()
            }
            ImGui.sameLine()
            ImGui.text(it.name)
        }
    }
}