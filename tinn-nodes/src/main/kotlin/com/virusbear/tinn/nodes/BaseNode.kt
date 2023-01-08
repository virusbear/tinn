package com.virusbear.tinn.nodes

import com.virusbear.tinn.*
import com.virusbear.tinn.events.NodeMovedEvent
import com.virusbear.tinn.math.IVec2
import java.util.*
import kotlin.reflect.KClass

abstract class BaseNode(
    override val name: String,
    override val identifier: NodeIdentifier,
    override val deletable: Boolean = true
): Node {
    final override var id: Int = -1
        set(value) {
            if(value == -1) {
                field = value
                return
            }
            require(_nodespace == null) { "Unable to set id of already attached node" }

            field = value
        }

    final override var position: IVec2 = IVec2.ZERO
        private set

    private val _ports = LinkedList<Port>()
    override val ports: List<Port>
        get() = _ports.toList()

    private var _nodespace: Nodespace? = null

    init {
        EventBus.subscribe<NodeMovedEvent> {
            if(it.node == this) {
                position = it.position
            }
        }
    }

    override val nodespace: Nodespace
        get() {
            val nodespace = _nodespace
            require(nodespace != null) { "Node is not yet attached to any nodespace or was already detached previously" }

            return nodespace
        }
    protected inline fun <reified T: Any?> input(
        name: String,
        default: T? = null
    ): PortDelegate<T> =
        PortDelegate(
            port(
                PortDirection.Input,
                name,
                T::class,
                default
            )
        )

    protected inline fun <reified T: Any?> output(
        name: String,
        default: T? = null
    ): PortDelegate<T> =
        PortDelegate(
            port(
                PortDirection.Output,
                name,
                T::class,
                default
            )
        )

    protected fun <T: Any?> port(
        direction: PortDirection,
        name: String,
        type: KClass<*>,
        default: T?
    ): Port =
        ports.firstOrNull {
            it.direction == direction && it.name == name
        } ?: Port(
            this,
            direction,
            name,
            type,
            default
        ).also { port ->
            _ports += port
        }

    protected operator fun minusAssign(port: Port) {
        _ports -= port
    }

    override fun onAttach(nodespace: Nodespace) {
        if(id == -1)
            id = nodespace.acquireNodeId()
        _nodespace = nodespace

        ports.forEach {
            it.onAttach(nodespace)
        }
    }

    override fun onDetach(nodespace: Nodespace) {
        ports.forEach {
            it.onDetach(nodespace)
        }

        nodespace.releaseNodeId(id)
        id = -1
    }

    override fun load(reader: SceneReader, context: Context) {
        val version = reader.string("version")
        require(SCENE_VERSION.version >= version.version) { "Unsupported file version. Unable to load BaseNode" }

        val id = reader.int("id")
        val position = reader.iVec2("pos")
        reader.list("ports") {
            val portName = string("name")
            val typeName = string("type")
            val type = Class.forName(typeName).kotlin
            val direction = string("direction")
            val portId = int("id")

            val port = port(
                PortDirection.valueOf(direction),
                portName,
                Class.forName(typeName).kotlin,
                NodeManager.loadPortValue("default", type, this)
            )

            port.id = portId

            if(direction == PortDirection.Input.toString()) {
                port.value = NodeManager.loadPortValue("value", type, this)
            }
        }

        this.id = id
        this.position = position
    }

    override fun save(writer: SceneWriter) {
        writer.write("version", SCENE_VERSION)
        writer.write("id", id)
        writer.write("pos", position)
        writer.writeList("ports", ports) {
            write("name", it.name)
            write("type", it.type.java.name)
            write("direction", it.direction.toString())
            write("id", it.id)
            NodeManager.savePortValue("default", it.type, it.default, this)

            if(it.direction == PortDirection.Input) {
                NodeManager.savePortValue("value", it.type, it.value, this)
            }
        }
    }

    companion object {
        private const val SCENE_VERSION = "0.0.1"
    }
}