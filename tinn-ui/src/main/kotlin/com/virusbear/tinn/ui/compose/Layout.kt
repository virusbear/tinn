package com.virusbear.tinn.ui.compose

import androidx.compose.runtime.Composable
import com.virusbear.tinn.ui.compose.modifier.Modifier
import com.virusbear.tinn.ui.compose.node.Node
import com.virusbear.tinn.ui.compose.node.NodeFactory

@Composable
internal fun Layout(
    modifiers: Modifier = Modifier,
    measurePolicy: MeasurePolicy,
    content: @Composable () -> Unit
) {
    Node(
        modifiers = modifiers,
        measurePolicy = measurePolicy,
        factory = NodeFactory,
        content = content
    )
}