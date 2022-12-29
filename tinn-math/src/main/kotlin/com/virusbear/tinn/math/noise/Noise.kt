package com.virusbear.tinn.math.noise

import com.virusbear.tinn.math.Vec3

interface Noise {
    fun sample(pos: Vec3): Double
}