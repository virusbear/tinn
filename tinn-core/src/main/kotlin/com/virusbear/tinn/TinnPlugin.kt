package com.virusbear.tinn

import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.PortSerializer
import com.virusbear.tinn.nodes.math.*
import com.virusbear.tinn.nodes.registerPortSerializers
import com.virusbear.tinn.registry.Identifier
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
        Registries.PortSerializers.registerPortSerializers()
    }
}

