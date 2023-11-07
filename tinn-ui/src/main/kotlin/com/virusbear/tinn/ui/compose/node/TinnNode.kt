package com.virusbear.tinn.ui.compose.node

import com.virusbear.tinn.draw.Drawable
import com.virusbear.tinn.draw.Drawer
import com.virusbear.tinn.ui.compose.*
import com.virusbear.tinn.ui.compose.androidx.Constraints
import com.virusbear.tinn.ui.compose.modifier.Modifier
import com.virusbear.tinn.ui.compose.modifier.*

internal class TinnNode(
    var measurePolicy: MeasurePolicy
): Measurable {
    companion object {
        fun createRootNode(): TinnNode =
            TinnNode(
                measurePolicy = BoxMeasurePolicy()
            )
    }

    val children = mutableListOf<TinnNode>()

    private val bottomLayer: TinnNodeLayer = BottomLayer(this)
    var topLayer: TinnNodeLayer = bottomLayer
        private set

    override var parentData: Any? = null
        private set

    var modifiers: Modifier = Modifier
        set(value) {
            topLayer = value.foldOut(bottomLayer) { element, lowerLayer ->
                when(element) {
                    is LayoutModifier -> LayoutLayer(element, lowerLayer)
                    is DrawModifier -> DrawLayer(element, lowerLayer)
                    is ParentDataModifier -> {
                        parentData = element.modifyParentData(parentData)
                        lowerLayer
                    }
                    else -> lowerLayer
                }
            }

            field = value
        }

    override fun measure(constraints: Constraints): Placeable =
        topLayer.apply { measure(constraints) }

    fun measureAndPlace(constraints: Constraints) {
        val placeable = measure(constraints)
        topLayer.run { placeable.place(0, 0) }
    }

    val width: Int
        get() = topLayer.width
    val height: Int
        get() = topLayer.height
    val x: Int
        get() = topLayer.x
    val y: Int
        get() = topLayer.y

    fun draw(drawer: Drawer) {
        topLayer.draw(drawer)
    }
}