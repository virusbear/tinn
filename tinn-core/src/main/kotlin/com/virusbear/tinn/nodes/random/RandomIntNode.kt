package com.virusbear.tinn.nodes.random

import com.virusbear.tinn.nodes.*
import com.virusbear.tinn.Context
import kotlin.random.Random

class RandomIntNode: BaseNode("Random Int", RandomIntNode) {
    @Register
    companion object: NodeIdentifier("Random Int", NodeCategory.Math, factory = { RandomIntNode() })

    var output: Int by output("Output", default = 0)
    val minimum: Int by input("Minimum", default = Int.MIN_VALUE)
    val maximum: Int by input("Minimum", default = Int.MAX_VALUE)

    private val random = Random(System.currentTimeMillis())

    override fun process(context: Context) {
        output = random.nextInt(minimum, maximum)
    }
}