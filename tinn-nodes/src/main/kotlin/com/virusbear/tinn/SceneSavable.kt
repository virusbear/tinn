package com.virusbear.tinn

interface SceneSavable {
    fun save(writer: SceneWriter)
    fun load(reader: SceneReader, context: ProcessingContext)
}