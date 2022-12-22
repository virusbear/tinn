package com.virusbear.tinn.nodes

open class GroupNode(name: String = "Group"): DynamicPortNode(name) {
    @Register
    companion object: NodeIdentifier("Group", NodeCategory.Utility, ::GroupNode)

    override fun process() {
        println(dynamicPorts.joinToString { it.type.simpleName ?: "" })
    }
}