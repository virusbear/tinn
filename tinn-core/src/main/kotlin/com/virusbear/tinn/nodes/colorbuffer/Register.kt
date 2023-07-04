package com.virusbear.tinn.nodes.colorbuffer

import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.registry.Registry

internal fun Registry<NodeIdentifier>.registerImageNodes() {
    register("tinn:calculate-pixel", CalculatePixelNode)
    register("tinn:fill-frame", FillFrameNode)
    register("tinn:frame-size", FrameSizeNode)
    register("tinn:get-colorbuffer", GetColorBufferNode)
    register("tinn:load-image", LoadImageNode)
    register("tinn:sample-texture", SampleTextureNode)
    register("tinn:save-image", SaveImageNode)
}