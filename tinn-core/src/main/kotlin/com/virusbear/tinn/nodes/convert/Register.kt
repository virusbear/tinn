package com.virusbear.tinn.nodes.convert

import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.registry.Registry

internal fun Registry<NodeIdentifier>.registerConversionNodes() {
    register("tinn:deg2rad", DegreesToRadiansNode)
    register("tinn:rad2deg", RadiansToDegreesNode)
    register("tinn:float2int", FloatToIntNode)
    register("tinn:int2float", IntToFloatNode)
    register("tinn:ivec2tovec2", IVec2ToVec2)
    register("tinn:vec2toivec2", Vec2ToIVec2)
}