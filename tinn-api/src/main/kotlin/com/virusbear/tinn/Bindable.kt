package com.virusbear.tinn

interface Bindable {
    fun bind()
    fun unbind()
    fun <T> bound(block: () -> T): T {
        bind()

        try {
            return block()
        } finally {
            unbind()
        }
    }
}