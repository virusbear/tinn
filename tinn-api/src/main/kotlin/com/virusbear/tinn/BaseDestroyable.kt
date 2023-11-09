package com.virusbear.tinn

abstract class BaseDestroyable: Destroyable {
    final override var destroyed: Boolean = false
    private set

    override fun destroy() {
        destroyed = true
    }
}

abstract class ContextAwareDestroyable: BaseDestroyable(), ContextAware, Context.OnDestroyListener {
    init {
        context.onDestroy(this)
    }

    override fun onDestroy() {
        destroy()
    }
}