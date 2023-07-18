package com.virusbear.tinn.ui.widgets

import com.virusbear.tinn.BaseDestroyable
import com.virusbear.tinn.Event
import com.virusbear.tinn.draw.Drawer
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.ui.*

abstract class Widget: BaseDestroyable() {
    var scene: Scene? = null
    var parent: Widget? = null

    var dirty: Boolean = false
        protected set(value) {
            if(value) {
                parent?.dirty = true
            }

            field = value
        }

    var visible: Boolean = true
    abstract val size: Vec2

    private val children = mutableListOf<Widget>()

    fun attach(widget: Widget) {
        require(widget.parent == null) { "Widget is already attached." }
        widget.scene = scene
        widget.parent = this

        dirty = true
    }

    fun detach(widget: Widget) {
        if(widget.parent != this) {
            return
        }

        widget.scene = null
        widget.parent = null

        dirty = true
    }

    open fun draw(drawer: Drawer) {
        dirty = false
    }

    open operator fun contains(pos: Vec2): Boolean {
        return pos.x in 0.0..size.x && pos.y in 0.0..size.y
    }

    override fun destroy() {
        children.forEach {
            detach(it)
            it.destroy()
        }

        super.destroy()
    }

    //TODO: how to focus single elements like text fields?

    open fun onAttach() {}
    open fun onDetach() {}

    private fun <T: Event> recurseEvent(handler: Widget.(T) -> Boolean, event: T): Boolean {
        for(widget in children) {
            if(widget.accept(event) && widget.handler(event)) {
                return true
            }
        }

        return false
    }

    open fun accept(event: Event): Boolean =
        when(event) {
            is MouseEvent -> event.position in this
            is MouseScrollEvent -> event.position in this
            is MouseMovedEvent -> event.endPosition in this
            is MouseDraggedEvent -> event.endPosition in this
            else -> true
        }

    //TODO: onMouseEntered and onMouseExited should be relative to the Widget the event is received on.
    //TODO: Instead on firing events for "OnEnter" and "OnExit" a flag "hovered" and "dragged" should be set on the widget to easily access the information passed by those events
    open fun onMouseEntered(event: MouseEnteredEvent): Boolean =
        recurseEvent(Widget::onMouseEntered, event)
    open fun onMouseExited(event: MouseExitedEvent): Boolean =
        recurseEvent(Widget::onMouseExited, event)
    open fun onMouseDown(event: MouseEvent): Boolean =
        recurseEvent(Widget::onMouseDown, event)
    open fun onMouseUp(event: MouseEvent): Boolean =
        recurseEvent(Widget::onMouseUp, event)
    open fun onMouseDragged(event: MouseDraggedEvent): Boolean =
        recurseEvent(Widget::onMouseDragged, event)
    open fun onMouseClicked(event: MouseEvent): Boolean =
        recurseEvent(Widget::onMouseClicked, event)
    open fun onMouseDoubleClicked(event: MouseEvent): Boolean =
        recurseEvent(Widget::onMouseDoubleClicked, event)
    open fun onMouseScroll(event: MouseScrollEvent): Boolean =
        recurseEvent(Widget::onMouseScroll, event)
    open fun onMouseMoved(event: MouseMovedEvent): Boolean =
        recurseEvent(Widget::onMouseMoved, event)

    open fun onKeyDown(event: KeyEvent): Boolean =
        recurseEvent(Widget::onKeyDown, event)
    open fun onKeyUp(event: KeyEvent): Boolean =
        recurseEvent(Widget::onKeyUp, event)
    open fun onKeyRepeated(event: KeyEvent): Boolean =
        recurseEvent(Widget::onKeyRepeated, event)
    open fun onKeyCharacter(event: KeyCharacterEvent): Boolean =
        recurseEvent(Widget::onKeyCharacter, event)

    open fun onWindowFocusGained(event: WindowFocusGainedEvent): Boolean =
        recurseEvent(Widget::onWindowFocusGained, event)
    open fun onWindowFocusLost(event: WindowFocusLostEvent): Boolean =
        recurseEvent(Widget::onWindowFocusLost, event)
}