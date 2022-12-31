package com.virusbear.tinn.nodes

import com.virusbear.tinn.SceneReader
import com.virusbear.tinn.SceneWriter
import kotlin.reflect.KClass

abstract class DynamicPortNode(
    override val name: String,
    identifier: NodeIdentifier? = null,
    deletable: Boolean = true,
    dynamicInputsAllowed: Boolean = true,
    dynamicOutputsAllowed: Boolean = true
): BaseNode(name, identifier, deletable) {
    private val _dynamicPorts = HashSet<Port>()

    var dynamicInputsAllowed: Boolean = dynamicInputsAllowed
        private set
    var dynamicOutputsAllowed: Boolean = dynamicOutputsAllowed
        private set

    val dynamicPorts: List<Port>
        get() = _dynamicPorts.toList()

    open fun <T: Any?> addPort(
        direction: PortDirection,
        name: String,
        type: KClass<*>,
        default: T?
    ): Port = port(
        direction,
        name,
        type,
        default
    ).also {
        when(direction) {
            PortDirection.Input -> if(!dynamicInputsAllowed) return@also
            PortDirection.Output -> if(!dynamicOutputsAllowed) return@also
        }
        it.onAttach(nodespace)
        _dynamicPorts += it
    }

    open fun removePort(
        port: Port
    ) {
        this -= port
        _dynamicPorts -= port
    }

    override fun load(reader: SceneReader, nodespace: Nodespace) {
        super.load(reader, nodespace)
        val version = reader.string("version")
        require(SCENE_VERSION >= version) { "Unsupported file version. Unable to load DynamicPortNode" }

        dynamicOutputsAllowed = reader.byte("dynOutputAllowed") != 0.toByte()
        dynamicInputsAllowed = reader.byte("dynInputAllowed") != 0.toByte()
        reader.list("dynPorts") {
            val name = string("name")
            val id = int("id")
            val type = string("type")
            val direction = string("direction")

            addPort(
                PortDirection.valueOf(direction),
                name,
                Class.forName(type).kotlin,
                null
            ).id = id
        }
    }

    override fun save(writer: SceneWriter) {
        super.save(writer)
        writer.write("version", SCENE_VERSION)
        writer.write("dynOutputAllowed", (if(dynamicOutputsAllowed) 1 else 0).toByte())
        writer.write("dynInputAllowed", (if(dynamicInputsAllowed) 1 else 0).toByte())
        writer.writeList("dynPorts", dynamicPorts) {
            write("name", it.name)
            write("id", it.id)
            write("type", it.type.qualifiedName!!)
            write("direction", it.direction.toString())
        }
    }

    companion object {
        private const val SCENE_VERSION = "0.0.1"
    }
}