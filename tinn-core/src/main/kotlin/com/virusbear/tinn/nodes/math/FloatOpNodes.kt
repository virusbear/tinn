package com.virusbear.tinn.nodes.math

import com.virusbear.tinn.Context
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.registry.Registry
import kotlin.math.*

class FloatOpNode(private val op: (Double) -> Double, name: String, identifier: NodeIdentifier): BaseNode(name, identifier) {
    val input: Double by input("Input", 0.0)
    var output: Double by output("Output", 0.0)

    override fun process(context: Context) {
        output = op(input)
    }
}

internal fun Registry<NodeIdentifier>.registerFloatOpNodes() {
    register("tinn:sqrt", SqrtNode)
    register("tinn:floor", FloorNode)
    register("tinn:ceil", CeilNode)
    register("tinn:sin", SinNode)
    register("tinn:cos", CosNode)
    register("tinn:abs", AbsNode)
    register("tinn:exp", ExpNode)
    register("tinn:tan", TanNode)
    register("tinn:ln", LnNode)
    register("tinn:log10", Log10Node)
    register("tinn:log2", Log2Node)
}

val SqrtNode = NodeIdentifier("Sqrt", NodeCategory.Math) { FloatOpNode(::sqrt, "Sqrt", it[NodeIdentifier]!!) }
val FloorNode = NodeIdentifier("Floor", NodeCategory.Math) { FloatOpNode(::floor, "Floor", it[NodeIdentifier]!!) }
val CeilNode = NodeIdentifier("Ceil", NodeCategory.Math) { FloatOpNode(::ceil, "Ceil", it[NodeIdentifier]!!) }
val SinNode = NodeIdentifier("Sin", NodeCategory.Math) { FloatOpNode(::sin, "Sin", it[NodeIdentifier]!!) }
val CosNode = NodeIdentifier("Cos", NodeCategory.Math) { FloatOpNode(::cos, "Cos", it[NodeIdentifier]!!) }
val AbsNode = NodeIdentifier("Abs", NodeCategory.Math) { FloatOpNode(::abs, "Abs", it[NodeIdentifier]!!) }
val ExpNode = NodeIdentifier("Exp", NodeCategory.Math) { FloatOpNode(::exp, "Exp", it[NodeIdentifier]!!) }
val TanNode = NodeIdentifier("Tan", NodeCategory.Math) { FloatOpNode(::tan, "Tan", it[NodeIdentifier]!!) }
val LnNode = NodeIdentifier("Ln", NodeCategory.Math) { FloatOpNode(::ln, "LN", it[NodeIdentifier]!!) }
val Log10Node = NodeIdentifier("Log 10", NodeCategory.Math) { FloatOpNode(::log10, "Log10", it[NodeIdentifier]!!) }
val Log2Node = NodeIdentifier("Log 2", NodeCategory.Math) { FloatOpNode(::log2, "Log2", it[NodeIdentifier]!!) }