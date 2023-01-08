package com.virusbear.tinn.ecs.api

import com.virusbear.tinn.ecs.tinn.Tinn

interface Registry {
    fun entity(): Entity
    operator fun minusAssign(entity: Entity)

    fun query(query: QueryBuilder.() -> Unit): Iterator<Entity>
}

fun target() {
    val tinn = Tinn()

    tinn.query {
        +PositionComponent
        -UndesiredComponent
    }
}

object PositionComponent: ComponentId<Float>
object UndesiredComponent: ComponentId<Unit>

class QueryBuilder {
    val componentFilter = HashMap<ComponentId<*>, (Any) -> Boolean>()

    fun components(vararg component: ComponentId<*>, mode: Mode = Mode.All)

    fun all(vararg component: ComponentId<*>) =
        components(*component)
    fun none(vararg component: ComponentId<*>) =
        components(mode = Mode.None, component = component)
    fun any(vararg component: ComponentId<*>) =
        components(mode = Mode.Any, component = component)

    fun filter(predicate: Entity.() -> Boolean)

    inline fun <reified T> filterComponent(component: ComponentId<T>, noinline predicate: (T) -> Boolean) {
        filterComponent(component) {
            if(it is T) {
                predicate(it)
            } else {
                true
            }
        }
    }

    fun filterComponent(component: ComponentId<*>, predicate: (Any) -> Boolean) {
        componentFilter[component] = predicate
    }

    operator fun ComponentId<*>.unaryPlus() {

    }

    operator fun ComponentId<*>.unaryMinus() {

    }

    internal fun build(): Query {
        TODO("Implement building query")
    }

    enum class Mode {
        Any,
        All,
        None
    }
}

class Query