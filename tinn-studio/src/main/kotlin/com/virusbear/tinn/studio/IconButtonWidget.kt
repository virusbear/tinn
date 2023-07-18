package com.virusbear.tinn.studio

import com.virusbear.tinn.draw.Drawable
import com.virusbear.tinn.draw.Drawer
import com.virusbear.tinn.math.Vec2
import com.virusbear.tinn.ui.MouseEvent
import com.virusbear.tinn.ui.theme.Theme
import com.virusbear.tinn.ui.widgets.Widget


class IconButtonWidget(
    private val icon: Drawable,
    private val action: () -> Unit
): Widget() {
    override val size: Vec2 = Vec2.ONE * Theme.IconSize()

    var active: Boolean = false
        set(value) {
            field = value
            dirty = true
        }
    var hovered: Boolean = false
        set(value) {
            field = value
            dirty = true
        }

    override fun draw(drawer: Drawer) {
        drawSurface(drawer)
        drawIcon(drawer)

        super.draw(drawer)
    }

    private fun drawSurface(drawer: Drawer) {
        drawer.fill = when {
            active -> Theme.IconActiveColor()
            hovered -> Theme.IconHoverColor()
            else -> null
        }
        drawer.translate(Vec2.ONE * Theme.IconMargin().toDouble())
        drawer.path {
            val margin = Theme.IconMargin() * 2.0
            roundedRect(Vec2.ZERO, Vec2(size.x - margin, size.y - margin), Theme.IconRounding().toDouble())
        }
    }

    private fun drawIcon(drawer: Drawer) {
        drawer.translate(Vec2.ONE * Theme.IconPadding().toDouble())
        val padding = (Theme.IconMargin() + Theme.IconPadding()) * 2.0
        val iconSize = Vec2(size.x - padding, size.y - padding)
        drawer.scale(Vec2(iconSize.x / icon.size.x, iconSize.y / icon.size.y))
        drawer.draw(icon)
    }

    override fun onMouseClicked(event: MouseEvent): Boolean {
        action()
        return true
    }
}