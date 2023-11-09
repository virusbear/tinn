package com.virusbear.tinn.nodes

import com.virusbear.tinn.ProcessingContext
import com.virusbear.tinn.SceneSavable
import com.virusbear.tinn.math.IVec2

interface Node: SceneSavable {
    val identifier: NodeIdentifier
    var id: Int
    val ports: List<Port>
    val name: String
    val nodespace: Nodespace
    val position: IVec2
    val deletable: Boolean

    fun process(context: ProcessingContext)

    fun onAttach(nodespace: Nodespace)
    fun onDetach(nodespace: Nodespace)
}