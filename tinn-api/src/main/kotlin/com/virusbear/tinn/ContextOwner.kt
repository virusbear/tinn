package com.virusbear.tinn

class ContextOwner(
    private val context: GraphicsContext
): Owner {
    override fun untrack(destroyable: Destroyable) {
        context.tracker.untrack(destroyable)
    }
}