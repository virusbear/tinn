package com.virusbear.tinn

import java.util.*
import kotlin.reflect.KClass

interface Event
typealias EventSubscriber = (Event) -> Unit

//TODO: Eventbus Subscription should be handled asynchronously. events should be published on one thread, passed to subscribers on a separate thread (coroutines?). Subscribers should then manually take care to switch to the correct context for their subscription.
//TODO: Multiple event busses should be added "EventBus.Window", "EventBus.Nodes" to free up a single event bus from receiving thousands of events. this also helps with shrinking the amount of subscribers iterated on each publish action as only subscribers get notified, that are actually interested in the event
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