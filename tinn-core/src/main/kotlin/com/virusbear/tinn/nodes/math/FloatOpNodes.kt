import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.Register
import kotlin.math.*

open class FloatOpNode(private val op: (Double) -> Double, name: String, identifier: NodeIdentifier): BaseNode(name, identifier) {
    val input: Double by input("Input", 0.0)
    var output: Double by output("Output", 0.0)

    override fun process() {
        output = op(input)
    }
}

@Register
val SqrtNode = NodeIdentifier("Sqrt", NodeCategory.Math) { FloatOpNode(::sqrt, "Sqrt", it) }
@Register
val FloorNode = NodeIdentifier("Floor", NodeCategory.Math) { FloatOpNode(::floor, "Floor", it) }
@Register
val CeilNode = NodeIdentifier("Ceil", NodeCategory.Math) { FloatOpNode(::ceil, "Ceil", it) }
@Register
val SinNode = NodeIdentifier("Sin", NodeCategory.Math) { FloatOpNode(::sin, "Sin", it) }
@Register
val CosNode = NodeIdentifier("Cos", NodeCategory.Math) { FloatOpNode(::cos, "Cos", it) }
@Register
val AbsNode = NodeIdentifier("Abs", NodeCategory.Math) { FloatOpNode(::abs, "Abs", it) }
@Register
val ExpNode = NodeIdentifier("Exp", NodeCategory.Math) { FloatOpNode(::exp, "Exp", it) }
@Register
val TanNode = NodeIdentifier("Tan", NodeCategory.Math) { FloatOpNode(::tan, "Tan", it) }
@Register
val LnNode = NodeIdentifier("Ln", NodeCategory.Math) { FloatOpNode(::ln, "LN", it) }
@Register
val Log10Node = NodeIdentifier("Log 10", NodeCategory.Math) { FloatOpNode(::log10, "Log10", it) }
@Register
val Log2Node = NodeIdentifier("Log 2", NodeCategory.Math) { FloatOpNode(::log2, "Log2", it) }