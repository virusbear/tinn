package com.virusbear.tinn.registry

class Registry<T: Any> internal constructor() {
    val entries = mutableMapOf<Identifier, T>()

    fun register(id: String, entry: T) {
        register(Identifier.fromString(id), entry)
    }

    fun register(id: Identifier, entry: T) {
        require(id !in entries) { "Instance with id $id already registered" }

        entries[id] = entry
    }

    fun entries(): List<T> =
        entries.values.toList()

    operator fun get(id: Identifier): T? =
        entries[id]

    operator fun get(id: String): T? =
        get(Identifier.fromString(id))
}