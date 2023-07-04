package com.virusbear.tinn.nodes.random.noise

import com.virusbear.tinn.math.Vec3
import com.virusbear.tinn.math.noise.Noise3D
import com.virusbear.tinn.nodes.*
import com.virusbear.tinn.Context
import com.virusbear.tinn.registry.Register

class Noise3DNode: BaseNode("Sample Noise 3D", Noise3DNode) {
    @Register("tinn:noise-3d")
    companion object: NodeIdentifier("Sample Noise 3D", NodeCategory.Utility, factory = { Noise3DNode() })

    val noise: Noise3D? by input("Noise", default = null)
    val pos: Vec3 by input("Position", default = Vec3.ZERO)
    val offset: Vec3 by input("Offset", default = Vec3.ZERO)
    val zoom: Double by input("Zoom", default = 1.0)
    var result: Vec3 by output("Output", default = Vec3.ZERO)

    override fun process(context: Context) {
        result = noise?.sample(offset + pos * zoom) ?: Vec3.ZERO
    }
}