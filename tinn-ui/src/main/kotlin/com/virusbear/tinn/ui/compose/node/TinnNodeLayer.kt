package com.virusbear.tinn.ui.compose.node

import com.virusbear.tinn.draw.Drawer
import com.virusbear.tinn.ui.compose.Measurable
import com.virusbear.tinn.ui.compose.MeasureScope
import com.virusbear.tinn.ui.compose.Placeable
import com.virusbear.tinn.ui.compose.PlacementScope

internal interface TinnNodeLayer: Measurable, Placeable, PlacementScope, MeasureScope {
    fun draw(drawer: Drawer)
}