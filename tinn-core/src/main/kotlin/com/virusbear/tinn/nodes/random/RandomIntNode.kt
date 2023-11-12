package com.virusbear.tinn.nodes.random

import com.virusbear.tinn.ProcessingContext
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import kotlin.random.Random

class RandomIntNode: BaseNode("Random Int", RandomIntNode) {
    companion object: NodeIdentifier("Random Int", NodeCategory.Math, factory = { RandomIntNode() })

    var output: Int by output("Output", default = 0)
    val minimum: Int by input("Minimum", default = Int.MIN_VALUE)
    val maximum: Int by input("Minimum", default = Int.MAX_VALUE)

    private val random = Random(System.currentTimeMillis())

    override fun process(context: ProcessingContext) {
        output = random.nextInt(minimum, maximum)
    }
}