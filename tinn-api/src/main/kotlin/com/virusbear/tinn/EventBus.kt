package com.virusbear.tinn

import java.util.*
import kotlin.reflect.KClass

interface Event
typealias EventSubscriber = (Event) -> Unit

object EventBus {
    private val subscribers = LinkedList<EventSubscriber>()

    fun publish(event: Event) {
        subscribers.forEach {
            it(event)
        }
    }

    inline fun <reified T: Event> subscribe(noinline subscriber: (T) -> Unit) {
        subscribe(T::class) { subscriber(it as T) }
    }

    fun <T: Event> subscribe(eventClass: KClass<T>, subscriber: EventSubscriber) {
        subscribers += {
            if(eventClass.isInstance(it)) {
                subscriber(it)
            }
        }
    }
}