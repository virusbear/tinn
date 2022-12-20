package com.virusbear.tinn.studio.panels

import com.virusbear.tinn.BaseDestroyable
import com.virusbear.tinn.EventBus
import com.virusbear.tinn.events.NodeDeselectEvent
import com.virusbear.tinn.events.NodeSelectEvent
import com.virusbear.tinn.nodes.Node
import com.virusbear.tinn.nodes.Port
import com.virusbear.tinn.nodes.PortDirection
import com.virusbear.tinn.ui.Panel
import com.virusbear.tinn.ui.UIContext

class Properties: Panel, BaseDestroyable() {
    override val name: String = "Properties"

    private var node: Node? = null

    init {
        EventBus.subscribe<NodeDeselectEvent> {
            node = null
        }
        EventBus.subscribe<NodeSelectEvent> {
            node = it.node
        }
    }

    override fun render(context: UIContext) {
        val node = node ?: return

        val inputs = node.ports.filter { it.direction == PortDirection.Input }

        for(input in inputs) {
            if(node.nodespace.linksForPort(input).isNotEmpty()) continue

            when(input.value) {
                is Int -> renderInt(context, input)
                is Double -> renderDouble(context, input)
                /*is IVec2 -> renderIVec2(context, input)
                is Vec2 -> renderVec2(context, input)
                is IVec3 -> renderIVec3(context, input)
                is Vec3 -> renderVec3(context, input)
                is IVec4 -> renderIVec4(context, input)
                is Vec4 -> renderVec4(context, input)
                is String -> renderString(context, input)*/
            }
        }
    }

    private fun renderInt(context: UIContext, input: Port) {
        context.int(input.name, setter = { input.value = it }, getter = { input.value as Int })
        context.separator()
    }

    private fun renderDouble(context: UIContext, input: Port) {
        context.double(input.name, setter = { input.value = it }, getter = { input.value as Double })
        context.separator()
    }
}