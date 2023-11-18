package com.virusbear.tinn

interface Owner {
    fun untrack(destroyable: Destroyable)
}