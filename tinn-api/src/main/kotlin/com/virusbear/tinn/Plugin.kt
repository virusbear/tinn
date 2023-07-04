package com.virusbear.tinn

//TODO: plugins need more metadata (version, author, dependencies, etc)
interface Plugin {
    fun onInitialize()
}