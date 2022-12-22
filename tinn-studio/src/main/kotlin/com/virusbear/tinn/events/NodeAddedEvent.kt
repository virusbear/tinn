package com.virusbear.tinn.events

import com.virusbear.tinn.Event
import com.virusbear.tinn.nodes.Node

data class NodeAddedEvent(
    val node: Node
): Event