package com.virusbear.tinn.shader

import com.virusbear.tinn.Destroyable

interface ShaderProgram: Destroyable {
    val binary: ShaderProgramBinary

    fun attach(shader: Shader)
    fun detach(shader: Shader)

    fun <T> use(block: ShaderProgram.() -> T): T
}