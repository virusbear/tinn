package com.virusbear.tinn.studio

import androidx.compose.runtime.*
import com.virusbear.tinn.Driver
import com.virusbear.tinn.MultiSample
import com.virusbear.tinn.color.Color
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.math.vec
import com.virusbear.tinn.opengl.DriverGL
import com.virusbear.tinn.ui.compose.Alignment
import com.virusbear.tinn.ui.compose.Box
import com.virusbear.tinn.ui.compose.dp
import com.virusbear.tinn.ui.compose.modifier.*
import com.virusbear.tinn.ui.tinnWindow
import com.virusbear.tinn.window.Window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.future.future
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

suspend fun main() {
    Driver.use(DriverGL())

    val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    val window: Window = Driver.use {
        init()
        runBlocking(dispatcher) {
            createWindow(512, 512, "tinn", resizable = true, vsync = true, multisample = MultiSample.None)
        }
    }

    println("Hello World")

    val completableFuture = CoroutineScope(dispatcher).future {
        tinnWindow(window, dispatcher) {
            Box(Modifier.padding(16.dp)) {
                Box(Modifier.size(1.dp, 1.dp).background(Color.WHITE), alignment = Alignment.TopStart)
                Box(Modifier.size(1.dp, 1.dp).background(Color.WHITE), alignment = Alignment.TopEnd)
                Box(Modifier.size(1.dp, 1.dp).background(Color.WHITE), alignment = Alignment.BottomStart)
                Box(Modifier.size(1.dp, 1.dp).background(Color.WHITE), alignment = Alignment.BottomEnd)
                Box(Modifier.size(1.dp, 1.dp).background(Color.WHITE), alignment = Alignment.Center)
            }
        }
    }.thenApply {
        Driver.driver.destroy()
    }

    Driver.driver.runEventLoop()

    completableFuture.cancel(true)
}