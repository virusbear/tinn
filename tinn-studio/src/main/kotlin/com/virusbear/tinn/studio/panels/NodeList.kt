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
        render(context, NodeManager.hierarchy.root)
    }

    private fun render(context: UIContext, element: NodeCategoryTree.Element) {
        context.treeView(element.category.name) {
            element.children.forEach { _, e ->
                render(context, e)
            }

            element.nodes.forEach { node ->
                context.treeLeaf(node.name) {
                    Nodespace.current += node.new()
                }
            }
        }
    }
}