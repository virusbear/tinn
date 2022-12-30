package com.virusbear.tinn.nodes.random.noise

import com.virusbear.tinn.math.Vec3
import com.virusbear.tinn.math.noise.Noise3D
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.Register
import jdk.jfr.Registered

class Noise3DNode: BaseNode("Sample Noise 3D", Noise3DNode) {
    @Register
    companion object: NodeIdentifier("Sample Noise 3D", NodeCategory.Utility, { Noise3DNode() })

    val noise: Noise3D? by input("Noise", default = null)
    val pos: Vec3 by input("Position", default = Vec3.ZERO)
    val offset: Vec3 by input("Offset", default = Vec3.ZERO)
    val zoom: Double by input("Zoom", default = 1.0)
    var result: Vec3 by output("Output", default = Vec3.ZERO)

    override fun process() {
        result = noise?.sample(offset + pos * zoom) ?: Vec3.ZERO
    }
}