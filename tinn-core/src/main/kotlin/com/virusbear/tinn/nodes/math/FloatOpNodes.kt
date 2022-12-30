package com.virusbear.tinn.nodes.math

import com.virusbear.tinn.SceneReader
import com.virusbear.tinn.nodes.*
import kotlin.math.*

class FloatOpNode(private val op: (Double) -> Double, base: BaseNode): Node by base {
    val input: Double by base.input("Input", 0.0)
    var output: Double by base.output("Output", 0.0)

    override fun process() {
        output = op(input)
    }
}

class FloatOpNodeFactory(private val op: (Double) -> Double, private val name: String): NodeFactory {
    override fun new(identifier: NodeIdentifier): Node =
        FloatOpNode(op, BaseNode(name, identifier))

    override fun load(reader: SceneReader): Node {
        TODO("Not yet implemented")
    }

}

@Register val SqrtNode = NodeIdentifier("Sqrt", NodeCategory.Math, FloatOpNodeFactory(::sqrt, "Sqrt"))
@Register val FloorNode = NodeIdentifier("Floor", NodeCategory.Math, FloatOpNodeFactory(::floor, "Floor"))
@Register val CeilNode = NodeIdentifier("Ceil", NodeCategory.Math, FloatOpNodeFactory(::ceil, "Ceil"))
@Register val SinNode = NodeIdentifier("Sin", NodeCategory.Math, FloatOpNodeFactory(::sin, "Sin"))
@Register val CosNode = NodeIdentifier("Cos", NodeCategory.Math, FloatOpNodeFactory(::cos, "Cos"))
@Register val AbsNode = NodeIdentifier("Abs", NodeCategory.Math, FloatOpNodeFactory(::abs, "Abs"))
@Register val ExpNode = NodeIdentifier("Exp", NodeCategory.Math, FloatOpNodeFactory(::exp, "Exp"))
@Register val TanNode = NodeIdentifier("Tan", NodeCategory.Math, FloatOpNodeFactory(::tan, "Tan"))
@Register val LnNode = NodeIdentifier("Ln", NodeCategory.Math, FloatOpNodeFactory(::ln, "LN"))
@Register val Log10Node = NodeIdentifier("Log 10", NodeCategory.Math, FloatOpNodeFactory(::log10, "Log10"))
@Register val Log2Node = NodeIdentifier("Log 2", NodeCategory.Math, FloatOpNodeFactory(::log2, "Log2"))