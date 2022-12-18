package com.virusbear.tinn.nodes

interface Node {
    val id: Int
    val ports: List<Port>
    val name: String
    fun process()
}