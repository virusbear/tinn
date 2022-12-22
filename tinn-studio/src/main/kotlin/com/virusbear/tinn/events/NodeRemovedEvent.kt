package com.virusbear.tinn.events

import com.virusbear.tinn.Event
import com.virusbear.tinn.nodes.Node

data class NodeRemovedEvent(
    val node: Node
): Event

