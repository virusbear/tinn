package com.virusbear.tinn.studio.panels

import com.virusbear.tinn.BaseDestroyable
import com.virusbear.tinn.EventBus
import com.virusbear.tinn.events.NodespaceActivateEvent
import com.virusbear.tinn.nodes.NodeCategoryTree
import com.virusbear.tinn.nodes.NodeManager
import com.virusbear.tinn.nodes.Nodespace
import com.virusbear.tinn.ui.Panel
import com.virusbear.tinn.ui.UIContext

class NodeList: Panel, BaseDestroyable() {
    override val name: String = "Nodes"

    private var nodespace: Nodespace? = null

    init {
        EventBus.subscribe<NodespaceActivateEvent> {
            nodespace = it.nodespace
        }
    }

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
                    nodespace?.let {
                        it += node.new()
                    }
                }
            }
        }
    }
}