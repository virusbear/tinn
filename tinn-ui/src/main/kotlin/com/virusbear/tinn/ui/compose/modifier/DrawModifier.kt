package com.virusbear.tinn.ui.compose.modifier

import com.virusbear.tinn.draw.Drawer
import com.virusbear.tinn.extensions.isolated

interface DrawModifier: Modifier.Element {
    fun ContentDrawScope.draw()
}

interface DrawScope: Drawer {
    val width: Int
    val height: Int
}

interface ContentDrawScope: DrawScope {
    fun drawContent()
}

internal abstract class AbstractDrawScope(
    drawer: Drawer,
    override val width: Int,
    override val height: Int
): DrawScope, Drawer by drawer

fun Modifier.drawBehind(
    onDraw: DrawScope.() -> Unit
): Modifier = this then DrawModifierElement(onDraw)

internal class DrawModifierElement(
    val block: DrawScope.() -> Unit
): DrawModifier {
    override fun ContentDrawScope.draw() {
        isolated {
            block()
        }
        isolated {
            drawContent()
        }
    }
}