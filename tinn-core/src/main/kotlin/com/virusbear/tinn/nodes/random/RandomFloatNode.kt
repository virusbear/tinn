package com.virusbear.tinn.nodes.random

import com.virusbear.tinn.nodes.*
import com.virusbear.tinn.Context
import kotlin.random.Random

class RandomFloatNode: BaseNode("Random Float", RandomFloatNode) {
    @Register
    companion object: NodeIdentifier("Random Float", NodeCategory.Math, factory = { RandomFloatNode() })

    var output: Double by output("Output", default = 0.0)
    val minimum: Double by input("Minimum", default = Double.MIN_VALUE)
    val maximum: Double by input("Minimum", default = Double.MAX_VALUE)

    private val random = Random(System.currentTimeMillis())

    override fun process(context: Context) {
        output = random.nextDouble(minimum, maximum)
    }
}