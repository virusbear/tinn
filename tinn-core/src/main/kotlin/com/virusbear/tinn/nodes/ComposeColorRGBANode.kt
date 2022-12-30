package com.virusbear.tinn.nodes

import com.virusbear.tinn.color.Color

class ComposeColorRGBANode: BaseNode("Compose Color RGBA", ComposeColorRGBANode) {
    @Register
    companion object: NodeIdentifier("Color RGBA", NodeCategory.Compose, { ComposeColorRGBANode() })

    val r: Double by input("R", default = 0.0)
    val g: Double by input("G", default = 0.0)
    val b: Double by input("B", default = 0.0)
    val a: Double by input("A", default = 1.0)
    var color: Color by output("Color", default = Color.TRANSPARENT)

    override fun process() {
        color = Color(r, g, b, a)
    }
}