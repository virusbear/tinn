package com.virusbear.tinn.nodes

import com.virusbear.tinn.EventBus
import com.virusbear.tinn.events.NodeMovedEvent
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.Vec2
import java.util.*
import kotlin.reflect.KClass

abstract class BaseNode(
    override val name: String
): Node {
    final override var id: Int = -1
    private set

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
    protected inline fun <reified T: Any> input(
        name: String,
        default: T? = null
    ): ReadOnlyPortDelegate<T> =
        ReadOnlyPortDelegate(
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
        Port(
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
        _nodespace = nodespace
        id = nodespace.acquireNodeId()

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
}