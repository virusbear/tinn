package com.virusbear.tinn.async

interface Task<out T> {
    fun await(): T
}