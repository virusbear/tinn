package com.virusbear.tinn.nodes.random.noise

import com.virusbear.tinn.math.Vec3
import com.virusbear.tinn.math.noise.Noise
import com.virusbear.tinn.nodes.*
import com.virusbear.tinn.Context
import com.virusbear.tinn.registry.Register

class NoiseNode: BaseNode("Sample Noise 1D", NoiseNode) {
    @Register("tinn:noise")
    companion object: NodeIdentifier("Sample Noise 1D", NodeCategory.Utility, factory = { NoiseNode() })

    val noise: Noise? by input("Noise", default = null)
    val pos: Vec3 by input("Position", default = Vec3.ZERO)
    val offset: Vec3 by input("Offset", default = Vec3.ZERO)
    val zoom: Double by input("Zoom", default = 1.0)
    var result: Double by output("Output", default = 0.0)

    override fun process(context: Context) {
        //TODO: optimize zoom calculation
        result = noise?.sample(offset + pos * (1.0 / zoom)) ?: 0.0
    }
}