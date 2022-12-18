package com.virusbear.tinn.nodes

import java.util.*
import kotlin.reflect.KClass

abstract class BaseNode(
    //TODO: make property
    override val name: String
): Node {
    override val id: Int = 0//TODO("Use ID Generator to generate unique port id")

    private val _ports = LinkedList<Port>()
    override val ports: List<Port>
        get() = _ports
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

    protected inline fun <reified T: Any> output(
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

    protected fun <T: Any> port(
        direction: PortDirection,
        name: String,
        type: KClass<T>,
        default: T?
    ): Port =
        Port(
            0,//TODO("Use ID Generator to generate unique port id"),
            this,
            direction,
            name,
            type,
            default
        ).also { port ->
            _ports += port
        }
}