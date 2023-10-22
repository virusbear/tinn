package com.virusbear.tinn.ui.compose

import androidx.compose.runtime.AbstractApplier

internal class Applier(
    root: Node
): AbstractApplier<Node>(root) {
    override fun insertBottomUp(index: Int, instance: Node) {
        //Ignored
    }

    override fun insertTopDown(index: Int, instance: Node) {
        current.children.add(index, instance)
    }

    override fun move(from: Int, to: Int, count: Int) {
        current.children.move(from, to, count)
    }

    override fun onClear() {
        current.children.clear()
    }

    override fun remove(index: Int, count: Int) {
        current.children.remove(index, count)
    }
}