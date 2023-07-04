package com.virusbear.tinn.events

import com.virusbear.tinn.Event
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.nodes.Node

data class NodeMovedEvent(
    val node: Node,
    val position: IVec2
): Event