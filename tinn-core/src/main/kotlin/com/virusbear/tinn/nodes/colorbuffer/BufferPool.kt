package com.virusbear.tinn.nodes.colorbuffer

abstract class BufferPool<DESCRIPTOR, ELEMENT>(
    private val allocator: (DESCRIPTOR) -> ELEMENT,
    private val deallocator: (ELEMENT) -> Unit
) {
    private val objects = LinkedHashMap<String, ELEMENT>()

    operator fun get(index: String): ELEMENT? =
        objects[index]

    operator fun get(index: String, descriptor: DESCRIPTOR): ELEMENT {
        if(index in objects) {
            recycle(index)
        }

        val element = allocator(descriptor)
        objects[index] = element

        return element
    }

    operator fun set(index: String, element: ELEMENT) {
        if(index in objects) {
            recycle(index)
        }

        objects[index] = element
    }

    fun recycle(index: String) {
        objects[index]?.let { element ->
            deallocator(element)
        }

        objects -= index
    }
}