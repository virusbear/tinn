package com.virusbear.tinn.nodes

data class NodeCategory(
    val parent: NodeCategory?,
    val name: String
) {
    companion object {
        val Root = NodeCategory(
            parent = null,
            name = "All"
        )

        val Math = NodeCategory(
            parent = Root,
            name = "Math"
        )

        val Convert = NodeCategory(
            parent = Math,
            name = "Convert"
        )

        val Utility = NodeCategory(
            parent = Root,
            name = "Utility"
        )

        val Compose = NodeCategory(
            parent = Utility,
            name = "Compose"
        )

        val Decompose = NodeCategory(
            parent = Utility,
            name = "Decompose"
        )

        val Shader = NodeCategory(
            parent = Root,
            name = "Shader"
        )

        val Compute = NodeCategory(
            parent = Shader,
            name = "Compute"
        )

        val Particle = NodeCategory(
            parent = Root,
            name = "Particle"
        )

        val System = NodeCategory(
            parent = Root,
            name = "System"
        )
    }
}