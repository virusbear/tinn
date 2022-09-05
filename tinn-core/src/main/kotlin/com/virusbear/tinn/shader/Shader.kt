package com.virusbear.tinn.shader

import com.virusbear.tinn.Destroyable

interface Shader: Destroyable {
    val binary: ShaderBinary
}