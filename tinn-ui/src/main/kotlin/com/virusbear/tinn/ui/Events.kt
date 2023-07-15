package com.virusbear.tinn.ui

import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.window.MouseButton
import com.virusbear.tinn.window.Mod
import com.virusbear.tinn.Event
import com.virusbear.tinn.window.Key

object MouseEnteredEvent: Event
object MouseExitedEvent: Event

data class MouseEvent(
    val button: MouseButton,
    val mods: Set<Mod> = emptySet(),
    val position: Vec2
): Event

data class MouseDraggedEvent(
    val button: MouseButton,
    val mods: Set<Mod> = emptySet(),
    val endPosition: Vec2,
    val delta: Vec2
): Event

data class MouseScrollEvent(
    val button: MouseButton? = null,
    val mods: Set<Mod> = emptySet(),
    val position: Vec2,
    val delta: Vec2
): Event

data class MouseMovedEvent(
    val endPosition: Vec2,
    val delta: Vec2
): Event

data class KeyEvent(
    val key: Key,
    val mods: Set<Mod> = emptySet()
): Event

data class KeyCharacterEvent(
    val character: Char,
    val mods: Set<Mod> = emptySet()
): Event

object WindowFocusGainedEvent: Event
object WindowFocusLostEvent: Event