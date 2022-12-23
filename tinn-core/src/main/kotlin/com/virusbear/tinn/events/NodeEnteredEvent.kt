package com.virusbear.tinn.events

import com.virusbear.tinn.Event
import com.virusbear.tinn.nodes.Node

data class NodeEnteredEvent(
    val node: Node
): Event