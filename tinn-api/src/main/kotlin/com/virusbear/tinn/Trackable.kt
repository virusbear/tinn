package com.virusbear.tinn

abstract class Trackable: BaseDestroyable() {
    init {
        Driver.driver.track(this)
    }

    override fun destroy() {
        if(destroyed)
            return

        Driver.driver.untrack(this)
        super.destroy()
    }
}