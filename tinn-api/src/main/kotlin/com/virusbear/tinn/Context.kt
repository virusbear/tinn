package com.virusbear.tinn

import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext

interface Context: Destroyable {
    val dispatcher: CoroutineDispatcher
    fun onDestroy(onDestroyListener: OnDestroyListener)

    fun interface OnDestroyListener {
        fun onDestroy()
    }
}

inline fun <T> Context.execute(crossinline block: suspend () -> T): T =
    runBlocking(dispatcher) {
        block()
    }

suspend inline fun <T> Context.async(crossinline block: suspend () -> T): Deferred<T> =
    withContext(dispatcher) {
        async {
            block()
        }
    }