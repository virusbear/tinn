package com.virusbear.tinn

import java.util.concurrent.CompletableFuture

interface RenderThread {
    fun <T> execute(block: () -> T): T
    fun <T> future(block: () -> T): CompletableFuture<T>
    fun schedule(block: () -> Unit)
}