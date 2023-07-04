package com.virusbear.tinn

import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.math.AddNode
import com.virusbear.tinn.registry.Identifier
import com.virusbear.tinn.registry.Registries
import com.virusbear.tinn.registry.Registry

val Registries.Nodes: Registry<NodeIdentifier>

class TinnPlugin: Plugin {
    override fun onInitialize() {
        Registries.Nodes.register("tinn:add", AddNode)
        Registries.Nodes.register("tinn:add", AddNode)
        Registries.Nodes.register("tinn:add", AddNode)
        Registries.Nodes.register("tinn:add", AddNode)
        Registries.Nodes.register("tinn:add", AddNode)
        Registries.Nodes.register("tinn:add", AddNode)
        Registries.Nodes.register("tinn:add", AddNode)
        Registries.Nodes.register("tinn:add", AddNode)
        Registries.Nodes.register("tinn:add", AddNode)
        Registries.Nodes.register("tinn:add", AddNode)
        Registries.Nodes.register("tinn:add", AddNode)
        Registries.Nodes.register("tinn:add", AddNode)
    }
}