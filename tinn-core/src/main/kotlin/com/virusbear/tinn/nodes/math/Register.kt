package com.virusbear.tinn.nodes.math

import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.registry.Registry

internal fun Registry<NodeIdentifier>.registerMathNodes() {
    registerFloatOpNodes()
    register("tinn:div", DivideNode)
    register("tinn:add", AddNode)
    register("tinn:lerp", LerpNode)
    register("tinn:mod", ModuloNode)
    register("tinn:multiply", MultiplyNode)
    register("tinn:pow", PowNode)
    register("tinn:subtract", SubtractNode)
}