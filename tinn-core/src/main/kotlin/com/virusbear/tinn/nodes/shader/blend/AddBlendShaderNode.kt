package com.virusbear.tinn.nodes.shader.blend

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.shader.ComputeShader

//TODO: analyze if Computeshaders provide a benefit over FragmentShaders with full screen triangles here
//TODO: implement Shaders for OpenGL
class BlendShaderNode(name: String, protected val shader: ComputeShader): BaseNode(name) {
    val a: ColorBuffer? by input("A", default = null)
    val b: ColorBuffer? by input("B", default = null)

    override fun process() {

    }
}