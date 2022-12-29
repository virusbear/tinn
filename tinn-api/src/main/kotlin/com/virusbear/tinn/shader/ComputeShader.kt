package com.virusbear.tinn.shader

import com.virusbear.tinn.math.IVec3

interface ComputeShader: Shader {
    fun execute(workGroupSize: IVec3) {
        execute(workGroupSize.x, workGroupSize.y, workGroupSize.z)
    }
    fun execute(workGroupSizeX: Int, workGroupSizeY: Int, workGroupSizeZ: Int)
}

