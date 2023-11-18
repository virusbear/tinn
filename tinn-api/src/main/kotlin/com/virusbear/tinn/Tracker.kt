package com.virusbear.tinn

class Tracker<T> {
    private val _tracked = mutableSetOf<T>()

    val tracked: Set<T>
        get() = _tracked

    fun track(trackable: T) {
        _tracked += trackable
    }
    fun untrack(trackable: T) {
        _tracked -= trackable
    }
}