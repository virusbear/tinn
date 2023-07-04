package com.virusbear.tinn.registry

data class Identifier internal constructor(val namespace: String, val id: String) {
    companion object {
        fun fromString(identifier: String): Identifier {
            val (namespace, id) = identifier.split(":", limit = 2)

            return Identifier(namespace, id)
        }
    }

    override fun toString(): String =
        "$namespace:$id"
}