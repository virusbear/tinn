package com.virusbear.tinn.nodes

data class NodeCategory(
    val parent: NodeCategory?,
    val name: String
)

val RootNodeCategory = NodeCategory(
    parent = null,
    name = "All"
)

val MathNodeCategory = NodeCategory(
    parent = RootNodeCategory,
    name = "Math"
)

val ConvertNodeCategory = NodeCategory(
    parent = MathNodeCategory,
    name = "Convert"
)

val ShaderNodeCategory = NodeCategory(
    parent = RootNodeCategory,
    name = "Shader"
)

val ComputeShaderNodeCategory = NodeCategory(
    parent = ShaderNodeCategory,
    name = "Compute"
)

val ParticleNodeCategory = NodeCategory(
    parent = RootNodeCategory,
    name = "Particle"
)

val SystemNodeCategory = NodeCategory(
    parent = RootNodeCategory,
    name = "System"
)