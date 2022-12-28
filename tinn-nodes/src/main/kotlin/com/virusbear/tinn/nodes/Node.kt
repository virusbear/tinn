package com.virusbear.tinn.nodes

import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.Vec2

interface Node {
    val id: Int
    val ports: List<Port>
    val name: String
    val nodespace: Nodespace
    val position: IVec2
    val deletable: Boolean

    fun process()

    fun onAttach(nodespace: Nodespace)
    fun onDetach(nodespace: Nodespace)
}