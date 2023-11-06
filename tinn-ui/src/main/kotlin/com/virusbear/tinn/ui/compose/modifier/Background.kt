package com.virusbear.tinn.ui.compose.modifier

import androidx.compose.runtime.Stable
import com.virusbear.tinn.color.Color
import com.virusbear.tinn.math.IVec2
import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.math.vec

@Stable
fun Modifier.background(
    color: Color
): Modifier = this then BackgroundModifier(color)

private class BackgroundModifier(
    private val color: Color
): DrawModifier {
    override fun ContentDrawScope.draw() {
        fill = color
        rectangle(Vec2.ZERO, IVec2(width, height).vec)
    }
}