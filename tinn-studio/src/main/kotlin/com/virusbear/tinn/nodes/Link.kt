package com.virusbear.tinn.nodes

class Link(
    val start: Port,
    val end: Port
) {
    val id: Int = TODO("Use ID Generator to generate unique port id")

    init {
        require(start.type == end.type) { "Unable to link ports with different types" }
    }
}