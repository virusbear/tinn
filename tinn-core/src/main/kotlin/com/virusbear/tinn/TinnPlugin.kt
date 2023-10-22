package com.virusbear.tinn

import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.PortSerializer
import com.virusbear.tinn.nodes.registerCoreNodes
import com.virusbear.tinn.nodes.registerPortSerializers
import com.virusbear.tinn.registry.Registries
import com.virusbear.tinn.registry.Registries.registry
import com.virusbear.tinn.registry.Registry

val Registries.Nodes: Registry<NodeIdentifier> by registry()
val Registries.PortSerializers: Registry<PortSerializer> by registry()

class TinnPlugin: Plugin {
    override fun onInitialize() {
        Registries.Nodes.registerCoreNodes()
        Registries.PortSerializers.registerPortSerializers()
    }
}

