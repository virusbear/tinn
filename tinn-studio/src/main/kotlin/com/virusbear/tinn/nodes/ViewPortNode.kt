package com.virusbear.tinn.nodes

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.Context
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.studio.panels.ViewPort

class ViewPortNode: BaseNode("Viewport", ViewPortNode) {
    companion object: NodeIdentifier("Viewport", NodeCategory.Utility, factory = { ViewPortNode() })

    private val frame: ColorBuffer? by input("Frame")
    private var size: IVec2 by output("Size")

    override fun process(context: Context) {
        size = IVec2(ViewPort.size.x, ViewPort.size.y)

        frame?.let {
            ViewPort.show(it)
        }
    }
}