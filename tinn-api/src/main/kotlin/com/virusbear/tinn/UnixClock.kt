package com.virusbear.tinn

class UnixClock: Clock {
    override val time: Time
        get() = Time(System.currentTimeMillis() / 1000.0)
}