package com.virusbear.tinn

data class VertexAttribute(
    val name: String,
    val components: Int,
    val type: VertexComponentType,
    val normalized: Boolean
) {
    val size: Int = components * type.bytes
}