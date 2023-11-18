package com.virusbear.tinn

abstract class OwnedDestroyable(
    private val owner: Owner
): BaseDestroyable() {
    override fun destroy() {
        owner.untrack(this)
        super.destroy()
    }
}