package com.virusbear.tinn.nodes.random

import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.Register
import kotlin.random.Random

class RandomFloatNode: BaseNode("Random Float") {
    @Register
    companion object: NodeIdentifier("Random Float", NodeCategory.Math, ::RandomFloatNode)

    var output: Double by output("Output", default = 0.0)
    val minimum: Double by input("Minimum", default = Double.MIN_VALUE)
    val maximum: Double by input("Minimum", default = Double.MAX_VALUE)

    private val random = Random(System.currentTimeMillis())

    override fun process() {
        output = random.nextDouble(minimum, maximum)
    }
}