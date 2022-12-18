package com.virusbear.tinn.nodes

import java.util.Stack

class Nodespace {
    companion object {
        val current: Nodespace
            get() {
                require(nodespaces.isNotEmpty()) { "No nodespace in current context" }

                return nodespaces.peek()
            }

        private val nodespaces = Stack<Nodespace>()

        fun push(nodespace: Nodespace) {
            nodespaces.push(nodespace)
        }
        fun pop() {
            nodespaces.pop()
        }
    }

    val nodes = mutableListOf<Node>()

    operator fun plusAssign(node: Node) {
        nodes += node
    }
}