package com.virusbear.tinn.registry

class Registry<T: Any> private constructor(
    private val elements: MutableMap<Identifier, T>
): Map<Identifier, T> by elements {
    constructor(): this(mutableMapOf())

    fun register(id: String, entry: T) {
        register(Identifier.fromString(id), entry)
    }

    fun register(id: Identifier, entry: T) {
        require(id !in elements) { "Instance with id $id already registered" }

        elements[id] = entry
    }

    fun values(): List<T> =
        elements.values.toList()

    operator fun get(id: String): T? =
        get(Identifier.fromString(id))
}