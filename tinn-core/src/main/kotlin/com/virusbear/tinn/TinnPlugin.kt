package com.virusbear.tinn

import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.PortSerializer
import com.virusbear.tinn.nodes.colorbuffer.registerImageNodes
import com.virusbear.tinn.nodes.constants.registerConstantNodes
import com.virusbear.tinn.nodes.convert.registerConversionNodes
import com.virusbear.tinn.nodes.math.registerMathNodes
import com.virusbear.tinn.nodes.registerCoreNodes
import com.virusbear.tinn.nodes.registerPortSerializers
import com.virusbear.tinn.registry.Registries
import com.virusbear.tinn.registry.Registry
import kotlin.properties.Delegates

val Registries.Nodes: Registry<NodeIdentifier> by lazy {
    Registry()
}
val Registries.PortSerializers: Registry<PortSerializer> by lazy {
    Registry()
}

class TinnPlugin: Plugin {
    override fun onInitialize() {
        Registries.Nodes.registerCoreNodes()
        Registries.PortSerializers.registerPortSerializers()
    }
}

