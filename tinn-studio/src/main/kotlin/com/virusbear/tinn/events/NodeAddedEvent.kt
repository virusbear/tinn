package com.virusbear.tinn.events

import com.virusbear.tinn.Event
import com.virusbear.tinn.nodes.Node
import com.virusbear.tinn.nodes.Nodespace

data class NodeAddedEvent(
    val nodespace: Nodespace,
    val node: Node
): Event