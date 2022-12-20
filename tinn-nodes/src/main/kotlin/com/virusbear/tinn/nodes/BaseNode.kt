package com.virusbear.tinn.nodes

import java.util.*
import kotlin.reflect.KClass

abstract class BaseNode(
    override val name: String
): Node {
    final override var id: Int = -1
    private set

    private val _ports = LinkedList<Port>()
    override val ports: List<Port>
        get() = _ports

    private var _nodespace: Nodespace? = null

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