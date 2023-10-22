package com.virusbear.tinn.ui

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.Snapshot
import com.virusbear.tinn.draw.Drawable
import kotlinx.coroutines.*

fun main() {

}

suspend fun tinnWindow(
    title: String = "Tinn",
    width: Int = 800,
    height: Int = 600,
    resizable: Boolean = true,
    body: suspend TinnUiScope.() -> Unit
): Unit = coroutineScope {
    //TODO: create and show tinn window

    var hasFrameAwaiters = false
    val clock = BroadcastFrameClock {
        hasFrameAwaiters = true
    }
    val job = Job(coroutineContext[Job])
    val composeContext = coroutineContext + clock + job

    val applier = Applier(root) {
        //TODO: render to window
        hasFrameAwaiters = false
    }

    val recomposer = Recomposer(composeContext)
    val composition = Composition(applier, recomposer)

    launch(start = CoroutineStart.UNDISPATCHED, context = composeContext) {
        recomposer.runRecomposeAndApplyChanges()
    }

    launch(context = composeContext) {
        //while window is open
        while(true) {
            //TODO: only if frame is ready
            clock.sendFrame(0L)
        }
    }

    coroutineScope {
        val scope = object: TinnUiScope, CoroutineScope by this {
            override fun setContent(content: () -> Unit) {
                composition.setContent(content)
            }
        }

        var snapshotNotificationsPending = false
        val observer: (stat: Any) -> Unit = {
            if(!snapshotNotificationsPending) {
                snapshotNotificationsPending = true
                launch {
                    snapshotNotificationsPending = false
                    Snapshot.sendApplyNotifications()
                }
            }
        }
        val snapshotObserverHandle = Snapshot.registerGlobalWriteObserver(observer)
        try {
            scope.body()
        } finally {
            snapshotObserverHandle.dispose()
        }
    }

    yield()
    yield()
    Snapshot.sendApplyNotifications()
    yield()
    yield()

    if(hasFrameAwaiters) {
        CompletableDeferred<Unit>().also {

            it.await()
        }
    }

    job.cancel()
    composition.dispose()
}

interface TinnUiScope {
    fun setContent(content: @Composable () -> Unit)
}