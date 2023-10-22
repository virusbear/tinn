package com.virusbear.tinn.ui

import com.virusbear.tinn.EventBus
import com.virusbear.tinn.Time
import com.virusbear.tinn.UnixClock
import com.virusbear.tinn.extensions.draw
import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.math.vec
import com.virusbear.tinn.ui.widgets.Widget
import com.virusbear.tinn.window.*

private data class Mouse(
    var inScene: Boolean = false,
    var position: Vec2 = Vec2.ZERO,
    var button: MouseButton? = null,
    var mods: Set<Mod> = emptySet(),
    var lastButtonEvent: WindowMouseButtonEvent? = null,
    var lastButtonEventPosition: Vec2 = Vec2.ZERO,
    var lastButtonEventTimestamp: Time = Time(0.0),
)

class Scene(
    private val window: Window,
    val content: Widget
) {
    private val mouse = Mouse()

    private val clock = UnixClock()

    init {
        content.scene = this

        EventBus.subscribe<WindowFocusEvent> {
            if(it.window != this.window) {
                return@subscribe
            }

            if(it.focused) {
                content.onWindowFocusGained(WindowFocusGainedEvent)
            } else {
                content.onWindowFocusLost(WindowFocusLostEvent)
            }
        }

        EventBus.subscribe<WindowMouseEnterEvent> {
            if(it.window != this.window) {
                return@subscribe
            }

            mouse.inScene = true
            content.onMouseEntered(MouseEnteredEvent)
        }

        EventBus.subscribe<WindowMouseLeaveEvent> {
            if(it.window != this.window) {
                return@subscribe
            }

            mouse.inScene = false
            content.onMouseExited(MouseExitedEvent)
        }

        EventBus.subscribe<WindowMouseMoveEvent> {
            if(it.window != this.window) {
                return@subscribe
            }

            if(mouse.inScene) {
                content.onMouseMoved(MouseMovedEvent(it.pos, it.pos - mouse.position))

                mouse.button?.let { button ->
                    content.onMouseDragged(MouseDraggedEvent(button, mouse.mods, it.pos, it.pos - mouse.position))
                }
            }

            mouse.position = it.pos
        }

        EventBus.subscribe<WindowMouseScrollEvent> {
            if(it.window != this.window) {
                return@subscribe
            }

            content.onMouseScroll(MouseScrollEvent(mouse.button, mouse.mods, mouse.position, it.delta))
        }

        EventBus.subscribe<WindowMouseButtonEvent> {
            if(it.window != this.window) {
                return@subscribe
            }

            mouse.lastButtonEvent = it
            mouse.lastButtonEventPosition = mouse.position
            mouse.lastButtonEventTimestamp = clock.time

            val event = MouseEvent(it.button, it.mods, mouse.position)

            when(it.action) {
                Action.Press -> {
                    mouse.button = it.button
                    mouse.mods = it.mods

                    content.onMouseDown(event)
                    //TODO: emit Mouse events and calculate clicked, doubleclicked, pressed and released events
                }
                Action.Release -> {
                    mouse.button = null
                    mouse.mods = emptySet()

                    content.onMouseUp(event)
                    //TODO: emit Mouse events and calculate clicked, doubleclicked, pressed and released events
                }
                else -> {}
            }
        }
    }

    fun run() {
        loop(window) {
            window.renderTarget.draw {
                content.draw(this, window.renderTarget.size.vec)
            }
        }
    }

    private fun loop(window: Window, block: () -> Unit) {
        window.bound {
            while (window.open) {
                window.clear()
                block()
                window.update()
            }
        }
    }
}

