package com.virusbear.tinn.nodes

import com.virusbear.tinn.nodes.colorbuffer.registerImageNodes
import com.virusbear.tinn.nodes.constants.registerConstantNodes
import com.virusbear.tinn.nodes.convert.registerConversionNodes
import com.virusbear.tinn.nodes.math.registerMathNodes
import com.virusbear.tinn.nodes.particles.ParticleNode
import com.virusbear.tinn.nodes.primitive.FloatNode
import com.virusbear.tinn.nodes.primitive.IntNode
import com.virusbear.tinn.nodes.primitive.TextNode
import com.virusbear.tinn.nodes.random.RandomFloatNode
import com.virusbear.tinn.nodes.random.RandomIntNode
import com.virusbear.tinn.nodes.random.noise.Noise2DNode
import com.virusbear.tinn.nodes.random.noise.Noise3DNode
import com.virusbear.tinn.nodes.random.noise.NoiseNode
import com.virusbear.tinn.nodes.random.noise.PerlinNoiseNode
import com.virusbear.tinn.registry.Registry

internal fun Registry<NodeIdentifier>.registerCoreNodes() {
    registerMathNodes()
    registerImageNodes()
    registerConstantNodes()
    registerConversionNodes()

    register("tinn:particle", ParticleNode)

    register("tinn:float", FloatNode)
    register("tinn:int", IntNode)
    register("tinn:text", TextNode)

    register("tinn:noise-2d", Noise2DNode)
    register("tinn:noise-3d", Noise3DNode)
    register("tinn:noise", NoiseNode)
    register("tinn:noise-perlin", PerlinNoiseNode)
    register("tinn:random-float", RandomFloatNode)
    register("tinn:random-int", RandomIntNode)

    register("tinn:compose-rgba", ComposeColorRGBANode)

    register("tinn:compose-vec2", ComposeVec2)
    register("tinn:compose-vec3", ComposeVec3)
    register("tinn:compose-vec4", ComposeVec4)
    register("tinn:compose-ivec2", ComposeIVec2)
    register("tinn:compose-ivec3", ComposeIVec3)
    register("tinn:compose-ivec4", ComposeIVec4)

    register("tinn:decompose-vec2", DecomposeVec2)
    register("tinn:decompose-vec3", DecomposeVec3)
    register("tinn:decompose-vec4", DecomposeVec4)
    register("tinn:decompose-ivec2", DecomposeIVec2)
    register("tinn:decompose-ivec3", DecomposeIVec3)
    register("tinn:decompose-ivec4", DecomposeIVec4)

    register("tinn:console-debug", ConsoleDebugNode)
    register("tinn:file", FileNode)
    register("tinn:foreach", ForeachNode)
    register("tinn:group", GroupNode)
    register("tinn:nodespace", NodespaceNode)
    register("tinn:program", ProgramNode)
}