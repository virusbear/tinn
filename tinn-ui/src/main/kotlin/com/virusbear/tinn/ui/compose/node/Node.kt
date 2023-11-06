package com.virusbear.tinn.ui.compose.node

import androidx.compose.runtime.Applier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import com.virusbear.tinn.ui.compose.*
import com.virusbear.tinn.ui.compose.modifier.Modifier

@Composable
internal inline fun Node(
    modifiers: Modifier,
    measurePolicy: MeasurePolicy,
    noinline factory: () -> TinnNode,
    content: @Composable () -> Unit = { }
) {
    ComposeNode<TinnNode, Applier<Any>>(
        factory = factory,
        update = {
            set(modifiers) { this.modifiers = modifiers }
            set(measurePolicy) { this.measurePolicy = measurePolicy }
        },
        content
    )
}

internal val NodeFactory: () -> TinnNode = {
    TinnNode(
        ThrowingPolicy
    )
}

private val ThrowingPolicy = MeasurePolicy { _, _ -> throw AssertionError() }