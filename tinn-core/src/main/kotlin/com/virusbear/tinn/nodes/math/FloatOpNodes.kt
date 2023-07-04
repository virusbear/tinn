package com.virusbear.tinn.nodes.math

import com.virusbear.tinn.nodes.*
import com.virusbear.tinn.Context
import com.virusbear.tinn.registry.Register
import kotlin.math.*

class FloatOpNode(private val op: (Double) -> Double, name: String, identifier: NodeIdentifier): BaseNode(name, identifier) {
    val input: Double by input("Input", 0.0)
    var output: Double by output("Output", 0.0)

    override fun process(context: Context) {
        output = op(input)
    }
}

@Register("tinn:sqrt")
val SqrtNode = NodeIdentifier("Sqrt", NodeCategory.Math) { FloatOpNode(::sqrt, "Sqrt", it[NodeIdentifier]!!) }
@Register("tinn:floor")
val FloorNode = NodeIdentifier("Floor", NodeCategory.Math) { FloatOpNode(::floor, "Floor", it[NodeIdentifier]!!) }
@Register("tinn:ceil")
val CeilNode = NodeIdentifier("Ceil", NodeCategory.Math) { FloatOpNode(::ceil, "Ceil", it[NodeIdentifier]!!) }
@Register("tinn:sin")
val SinNode = NodeIdentifier("Sin", NodeCategory.Math) { FloatOpNode(::sin, "Sin", it[NodeIdentifier]!!) }
@Register("tinn:cos")
val CosNode = NodeIdentifier("Cos", NodeCategory.Math) { FloatOpNode(::cos, "Cos", it[NodeIdentifier]!!) }
@Register("tinn:abs")
val AbsNode = NodeIdentifier("Abs", NodeCategory.Math) { FloatOpNode(::abs, "Abs", it[NodeIdentifier]!!) }
@Register("tinn:exp")
val ExpNode = NodeIdentifier("Exp", NodeCategory.Math) { FloatOpNode(::exp, "Exp", it[NodeIdentifier]!!) }
@Register("tinn:tan")
val TanNode = NodeIdentifier("Tan", NodeCategory.Math) { FloatOpNode(::tan, "Tan", it[NodeIdentifier]!!) }
@Register("tinn:ln")
val LnNode = NodeIdentifier("Ln", NodeCategory.Math) { FloatOpNode(::ln, "LN", it[NodeIdentifier]!!) }
@Register("tinn:log10")
val Log10Node = NodeIdentifier("Log 10", NodeCategory.Math) { FloatOpNode(::log10, "Log10", it[NodeIdentifier]!!) }
@Register("tinn:log2")
val Log2Node = NodeIdentifier("Log 2", NodeCategory.Math) { FloatOpNode(::log2, "Log2", it[NodeIdentifier]!!) }