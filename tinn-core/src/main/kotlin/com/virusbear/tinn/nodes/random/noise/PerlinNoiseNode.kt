package com.virusbear.tinn.nodes.random.noise

import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.Register

class PerlinNoiseNode: BaseNode("Perlin Noise") {
    @Register
    companion object: NodeIdentifier("Perlin Noise", NodeCategory.Utility, ::PerlinNoiseNode)

    val x: Double by input("X", default = 0.0)
    val y: Double by input("Y", default = 0.0)
    val z: Double by input("Z", default = 0.0)
    var noise: Double by output("Noise", default = 0.0)

    override fun process() {
        TODO("Not yet implemented")
    }
}