package com.virusbear.tinn.ecs.api

interface Entity {
    val id: EntityId

    operator fun minusAssign(key: ComponentId<*>)

    operator fun <C> set(key: ComponentId<C>, component: C)
    operator fun <C> get(key: ComponentId<C>): C?
    operator fun contains(key: ComponentId<*>): Boolean
}