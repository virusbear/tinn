package com.virusbear.tinn.nodes.shader.blend

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.Context
import com.virusbear.tinn.shader.ComputeShader

//TODO: analyze if Computeshaders provide a benefit over FragmentShaders with full screen triangles here
//TODO: implement Shaders for OpenGL
class BlendShaderNode(name: String, protected val shader: ComputeShader, identifier: NodeIdentifier): BaseNode(name, identifier) {
    val a: ColorBuffer? by input("A", default = null)
    val b: ColorBuffer? by input("B", default = null)

    override fun process(context: Context) {

    }
}