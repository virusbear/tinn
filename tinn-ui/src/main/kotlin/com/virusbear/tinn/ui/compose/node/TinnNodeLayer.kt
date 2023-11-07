package com.virusbear.tinn.ui.compose.node

import com.virusbear.tinn.draw.Drawer
import com.virusbear.tinn.ui.compose.*

internal interface TinnNodeLayer: Measurable, Placeable, PlacementScope, MeasureScope {
    fun draw(drawer: Drawer)
}