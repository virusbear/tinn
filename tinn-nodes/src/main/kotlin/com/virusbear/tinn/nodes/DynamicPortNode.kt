package com.virusbear.tinn.nodes

import com.virusbear.tinn.Context
import com.virusbear.tinn.SceneReader
import com.virusbear.tinn.SceneWriter
import kotlin.reflect.KClass

abstract class DynamicPortNode(
    override val name: String,
    identifier: NodeIdentifier,
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

    override fun load(reader: SceneReader, context: Context) {
        super.load(reader, context)

        val version = reader.string("version")
        require(SCENE_VERSION >= version) { "Unsupported file version. Unable to load DynamicPortNode" }

        dynamicOutputsAllowed = reader.byte("dynOutputAllowed") != 0.toByte()
        dynamicInputsAllowed = reader.byte("dynInputAllowed") != 0.toByte()
        _dynamicPorts.addAll(
            reader.intArray("dynPorts").map { portId ->
                context[NodespaceContextElement]?.nodespace?.portByIdOrNull(portId)
            }.filterNotNull()
        )
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