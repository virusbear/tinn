package com.virusbear.tinn.nodes.particles

import com.virusbear.tinn.math.Vec3
import com.virusbear.tinn.nodes.*
import com.virusbear.tinn.Context
import com.virusbear.tinn.registry.Register

data class Particle(
    val acceleration: Vec3,
    val velocity: Vec3,
    val position: Vec3,
    val size: Double,
    val age: Double
)

class ParticleNode: BaseNode("Particle", ParticleNode) {
    @Register("tinn:particle")
    companion object: NodeIdentifier("Particle", NodeCategory.Utility, factory = { ParticleNode() })

    val particle: Particle? by input("Particle", default = null)
    var pos: Vec3 by output("Position", default = Vec3.ZERO)
    var velocity: Vec3 by output("Velocity", default = Vec3.ZERO)
    var acceleration: Vec3 by output("Acceleration", default = Vec3.ZERO)
    var size: Double by output("Size", default = 0.0)
    var age: Double by output("Age", default = 0.0)

    override fun process(context: Context) {
        particle?.let {
            pos = it.position
            velocity = it.velocity
            acceleration = it.acceleration
            size = it.size
            age = it.age
        }
    }
}