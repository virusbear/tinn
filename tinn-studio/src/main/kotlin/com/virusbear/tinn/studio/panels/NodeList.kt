package com.virusbear.tinn.studio.panels

import com.virusbear.tinn.BaseDestroyable
import com.virusbear.tinn.nodes.NodeCategoryTree
import com.virusbear.tinn.nodes.NodeManager
import com.virusbear.tinn.nodes.Nodespace
import com.virusbear.tinn.ui.Panel
import com.virusbear.tinn.ui.UIContext
import imgui.ImGui
import imgui.flag.ImGuiMouseButton
import imgui.flag.ImGuiTreeNodeFlags

class NodeList: Panel, BaseDestroyable() {
    override val name: String = "Nodes"

    override fun render(context: UIContext) {
        render(NodeManager.hierarchy.root)
    }

    private fun render(element: NodeCategoryTree.Element) {
        //TODO: implement keyboard navigation
        //TODO: implement selection of items
        if(ImGui.treeNode(element.category.name)) {
            element.children.forEach { (_, e) ->
                render(e)
            }

            element.nodes.forEach {
                if(ImGui.treeNodeEx(it.name, ImGuiTreeNodeFlags.Leaf)) {
                    if(ImGui.isItemClicked(ImGuiMouseButton.Left) && ImGui.isMouseDoubleClicked(ImGuiMouseButton.Left)) {
                        Nodespace.current += it.new()
                    }

                    ImGui.treePop()
                }
            }

            ImGui.treePop()
        }
    }
}