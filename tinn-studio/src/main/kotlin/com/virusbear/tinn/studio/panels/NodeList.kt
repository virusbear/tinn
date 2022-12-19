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
                    ImGui.treePop()

                    ImGui.getMousePos()

                    if(ImGui.isMouseDoubleClicked(ImGuiMouseButton.Left)) {
                        val mousePos = ImGui.getMousePos()
                        val rectMin = ImGui.getItemRectMin()
                        val rectMax = ImGui.getItemRectMax()

                        if(mousePos.x in rectMin.x .. rectMax.x && mousePos.y in rectMin.y .. rectMax.y) {
                            Nodespace.current += it.new()
                        }
                    }
                }
            }

            ImGui.treePop()
        }
    }
}