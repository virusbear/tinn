package com.virusbear.tinn.events

import com.virusbear.tinn.Event
import com.virusbear.tinn.nodes.Nodespace

data class NodespaceActivateEvent(
    val nodespace: Nodespace?
): Event