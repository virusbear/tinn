package com.virusbear.tinn.math.noise

import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.math.Vec3

interface Noise2D {
    fun sample(pos: Vec3): Vec2
}