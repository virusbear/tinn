package com.virusbear.tinn.nodes

import java.util.*

class IdPool {
    private val ids = BitSet()

    fun acquire(): Int =
        synchronized(ids) {
            ids.nextClearBit(0).also {
                ids.set(it)
            }
        }

    fun acquire(id: Int) {
        synchronized(ids) {
            ids.set(id)
        }
    }

    fun release(id: Int) {
        synchronized(ids) {
            ids.clear(id)
        }
    }

    fun free() {
        ids.clear()
    }
}