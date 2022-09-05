package com.virusbear.tinn

import java.util.LinkedList

class VertexFormat {
    private val attributes: MutableList<VertexAttribute> = LinkedList()

    fun attributes(): List<VertexAttribute> =
        attributes

    val size: Int
        get() = attributes.sumOf { it.size }

    var interleaved: Boolean = true

    fun attribute(name: String, components: Int, type: VertexComponentType = VertexComponentType.FLOAT, normalized: Boolean = false) {
        require(components in listOf(1, 2, 3, 4)) { "VertexAttribute must be one of {1, 2, 3, 4}. Got $components" }

        val va = VertexAttribute(name, components, type, normalized)
        val idx = attributes.indexOfFirst { it.name == name }
        if(idx >= 0) {
            attributes[idx] = va
        } else{
            attributes += va
        }
    }

    fun position(components: Int = 3) {
        attribute("position", components)
    }

    fun normal(components: Int = 3) {
        attribute("normal", components)
    }

    fun color(components: Int = 4) {
        attribute("color", components)
    }

    fun texCoord(tex: Int = 0) {
        attribute("texCoord$tex", 2)
    }
}