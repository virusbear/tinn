package com.virusbear.tinn.nodes.random.noise

import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.math.Vec3
import com.virusbear.tinn.math.noise.Noise2D
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.Register
import jdk.jfr.Registered

class Noise2DNode: BaseNode("Sample Noise 2D") {
    @Register
    companion object: NodeIdentifier("Sample Noise 2D", NodeCategory.Utility, ::Noise2DNode)

    val noise: Noise2D? by input("Noise", default = null)
    val pos: Vec3 by input("Position", default = Vec3.ZERO)
    val offset: Vec3 by input("Offset", default = Vec3.ZERO)
    val zoom: Double by input("Zoom", default = 1.0)
    var result: Vec2 by output("Output", default = Vec2.ZERO)

    override fun process() {
        result = noise?.sample(offset + pos * zoom) ?: Vec2.ZERO
    }
}