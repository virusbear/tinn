package com.virusbear.tinn.ui.compose

import androidx.compose.runtime.AbstractApplier
import com.virusbear.tinn.ui.compose.node.TinnNode

internal class Applier(
    root: TinnNode
): AbstractApplier<TinnNode>(root) {
    override fun insertBottomUp(index: Int, instance: TinnNode) {
        //Ignored
    }

    override fun insertTopDown(index: Int, instance: TinnNode) {
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