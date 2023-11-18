package com.virusbear.tinn

class DriverOwner(
    private val driver: Driver
): Owner {
    override fun untrack(destroyable: Destroyable) {
        driver.untrack(destroyable)
    }
}