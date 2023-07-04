package com.virusbear.tinn.nodes.random.noise

import com.virusbear.tinn.Context
import com.virusbear.tinn.math.noise.Noise
import com.virusbear.tinn.math.noise.PerlinNoise
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier

class PerlinNoiseNode: BaseNode("Perlin Noise", PerlinNoiseNode) {
    companion object: NodeIdentifier("Perlin Noise", NodeCategory.Utility, factory = { PerlinNoiseNode() })

    val octaves: Int by input("Octaves", default = 1)
    val frequency: Double by input("Frequency", default = 1.0)
    val amplitude: Double by input("Amplitude", default = 1.0)
    val persistence: Double by input("Persistence", default = 0.5)
    var noise: Noise by output("Noise", default = PerlinNoise())

    override fun process(context: Context) {
        noise = PerlinNoise(octaves, frequency, amplitude, persistence)
    }
}