package com.virusbear.tinn.nodes

import com.virusbear.tinn.Context
import com.virusbear.tinn.SceneReader
import com.virusbear.tinn.SceneWriter
import kotlin.reflect.KClass

typealias NodeFactory = (Context) -> Node

object NodeManager {
    val hierarchy = NodeCategoryTree()
    private val _portTypes = HashSet<KClass<*>>()
    private val _serializers = HashSet<PortSerializer>()
    val portTypes: Set<KClass<*>>
        get() = _portTypes
    val serializers: Set<PortSerializer>
        get() = _serializers

    fun register(identifier: NodeIdentifier) {
        hierarchy += identifier

        try {
            identifier.new(identifier).ports.forEach { port ->
                if(_serializers.none { it.type == port.type }) {
                    //TODO: logging; msg: No serializer defined for type ${port.type.simplename} values for this port will not be stored when saving scene files
                }
                registerPortType(port.type)
            }
        } catch(ex: Exception) {
            //IGNORED
            //TODO: find better way to load available port types
        }
    }

    fun register(serializer: PortSerializer) {
        if(_serializers.any { it.type == serializer.type }) {
            //TODO: logging; msg: Serializer for type ${serializer.type.simplename} already defined. skipping
        }

        _serializers += serializer
    }

    fun registerPortType(type: KClass<*>) {
        _portTypes += type
    }

    fun resolveNodeIdentifier(category: String, name: String): NodeIdentifier? {
        var identifier: NodeIdentifier? = null
        val nodeCategory = NodeCategory.fromString(category)
        hierarchy.walk({}) {
            if(it.category == nodeCategory && it.name == name) {
                identifier = it
            }
        }

        return identifier
    }

    fun walk(onCategory: (NodeCategory) -> Unit, onNode: (NodeIdentifier) -> Unit) {
        hierarchy.walk(onCategory, onNode)
    }

    fun savePortValue(key: String, type: KClass<*>, value: Any?, writer: SceneWriter) {
        if(value == null) return

        val serializer = serializers.firstOrNull { it.type == type } ?: return

        serializer.serialize(key, value, writer)
    }

    fun loadPortValue(key: String, type: KClass<*>, reader: SceneReader): Any? {
        if(key !in reader) return null

        val serializer = serializers.firstOrNull { it.type == type } ?: return null

        return serializer.deserialize(key, reader)
    }
}