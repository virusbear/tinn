package com.virusbear.tinn

import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.PortSerializer
import com.virusbear.tinn.nodes.colorbuffer.registerImageNodes
import com.virusbear.tinn.nodes.constants.registerConstantNodes
import com.virusbear.tinn.nodes.convert.registerConversionNodes
import com.virusbear.tinn.nodes.math.registerMathNodes
import com.virusbear.tinn.nodes.registerPortSerializers
import com.virusbear.tinn.registry.Registries
import com.virusbear.tinn.registry.Registry

val Registries.Nodes: Registry<NodeIdentifier> by lazy {
    TODO("Not yet implemented")
}
val Registries.PortSerializers: Registry<PortSerializer> by lazy {
    TODO("Not yet implemented")
}

class TinnPlugin: Plugin {
    override fun onInitialize() {
        Registries.Nodes.registerMathNodes()
        Registries.Nodes.registerImageNodes()
        Registries.Nodes.registerConstantNodes()
        Registries.Nodes.registerConversionNodes()
        Registries.PortSerializers.registerPortSerializers()
    }
}

