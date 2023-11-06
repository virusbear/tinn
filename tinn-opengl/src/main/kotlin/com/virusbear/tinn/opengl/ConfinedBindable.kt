package com.virusbear.tinn.opengl

import com.virusbear.tinn.Bindable
import com.virusbear.tinn.Driver

interface ConfinedBindable: Bindable {
    override fun <T> bound(block: () -> T): T =
        Driver.driver.scheduler.execute {
            super.bound(block)
        }
}