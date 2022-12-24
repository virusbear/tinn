package com.virusbear.tinn.nodes.math

import com.virusbear.tinn.nodes.*
import kotlin.math.*

open class FloatOpNode(private val op: (Double) -> Double, name: String): BaseNode(name) {
    val input: Double by input("Input", 0.0)
    var output: Double by output("Output", 0.0)

    override fun process() {
        output = op(input)
    }
}

@Register val SqrtNode = NodeIdentifier("Sqrt", NodeCategory.Math) { FloatOpNode(::sqrt, "Sqrt") }
@Register val FloorNode = NodeIdentifier("Floor", NodeCategory.Math) { FloatOpNode(::floor, "Floor") }
@Register val CeilNode = NodeIdentifier("Ceil", NodeCategory.Math) { FloatOpNode(::ceil, "Ceil") }
@Register val SinNode = NodeIdentifier("Sin", NodeCategory.Trigonometry) { FloatOpNode(::sin, "Sin") }
@Register val CosNode = NodeIdentifier("Cos", NodeCategory.Trigonometry) { FloatOpNode(::cos, "Cos") }
@Register val AbsNode = NodeIdentifier("Abs", NodeCategory.Math) { FloatOpNode(::abs, "Abs") }
@Register val ExpNode = NodeIdentifier("Exp", NodeCategory.Math) { FloatOpNode(::exp, "Exp") }
@Register val TanNode = NodeIdentifier("Tan", NodeCategory.Math) { FloatOpNode(::tan, "Tan") }
@Register val LnNode = NodeIdentifier("Ln", NodeCategory.Math) { FloatOpNode(::ln, "LN") }
@Register val Log10Node = NodeIdentifier("Log 10", NodeCategory.Math) { FloatOpNode(::log10, "Log10") }
@Register val Log2Node = NodeIdentifier("Log 2", NodeCategory.Math) { FloatOpNode(::log2, "Log2") }