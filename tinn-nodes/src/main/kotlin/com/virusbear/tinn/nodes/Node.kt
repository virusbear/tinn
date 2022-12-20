package com.virusbear.tinn.nodes

interface Node {
    val id: Int
    val ports: List<Port>
    val name: String
    val nodespace: Nodespace
    fun process()

    fun onAttach(nodespace: Nodespace)
    fun onDetach(nodespace: Nodespace)
}