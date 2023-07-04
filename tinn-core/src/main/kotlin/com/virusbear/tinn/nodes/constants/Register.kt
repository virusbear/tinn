package com.virusbear.tinn.nodes.constants

import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.registry.Registry

internal fun Registry<NodeIdentifier>.registerConstantNodes() {
    register("tinn:constant-e", ConstantENode)
    register("tinn:constant-pi", ConstantPINode)
}