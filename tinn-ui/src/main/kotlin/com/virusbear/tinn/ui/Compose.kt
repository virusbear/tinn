package com.virusbear.tinn.ui

import androidx.compose.runtime.BroadcastFrameClock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ControlledComposition
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.snapshots.Snapshot
import com.virusbear.tinn.ui.compose.*
import com.virusbear.tinn.ui.compose.GlobalSnapshotManager
import com.virusbear.tinn.ui.compose.TinnApplier
import com.virusbear.tinn.ui.compose.node.TinnNode
import com.virusbear.tinn.window.Window
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlin.coroutines.CoroutineContext

suspend fun tinnWindow(window: Window, uiContext: CoroutineContext, content: @Composable () -> Unit) {
    renderComposable(
        onUpdate = { node ->
            runBlocking(uiContext) {
                node.density = Density(window.dpi / Dp.ReferenceDensity.density)
                node.measureAndPlace(Constraints(0, window.width, 0, window.height))
                window.clear()
                with(window.renderTarget.drawer) {
                    begin(window.width, window.height, window.contentScale)
                    node.draw(this)
                    end()
                }
                window.update()
            }
        },
        content = content)
}

internal suspend fun renderComposable(onUpdate: (TinnNode) -> Unit, content: @Composable () -> Unit) = coroutineScope {
    GlobalSnapshotManager.ensureStarted()

    val frameChannel = Channel<Unit>()
    val clock = BroadcastFrameClock {
        frameChannel.trySend(Unit)
    }

    val recomposerContext = clock + Dispatchers.Default
    val recomposer = Recomposer(recomposerContext)

    val rootNode = TinnNode.createRootNode()
    val composition = ControlledComposition(TinnApplier(rootNode), parent = recomposer)

    composition.setContent(content)

    launch(recomposerContext, start = CoroutineStart.UNDISPATCHED) {
        recomposer.runRecomposeAndApplyChanges()
    }

    launch(recomposerContext) {
        while(true) {
            clock.sendFrame(System.nanoTime())
            onUpdate(rootNode)
        }
    }
}