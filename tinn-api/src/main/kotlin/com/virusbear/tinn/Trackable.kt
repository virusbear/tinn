package com.virusbear.tinn

abstract class Trackable(
    val driver: Driver
): BaseDestroyable() {
    init {
        driver.track(this)
    }

    override fun destroy() {
        if(destroyed)
            return

        driver.untrack(this)
        super.destroy()
    }
}