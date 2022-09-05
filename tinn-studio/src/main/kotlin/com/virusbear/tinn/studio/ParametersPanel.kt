package com.virusbear.tinn.studio

import com.virusbear.tinn.ui.Panel
import com.virusbear.tinn.ui.UIContext

class ParametersPanel(override val name: String): Panel {
    var active = false
    val value = IntArray(1)

    override fun render(context: UIContext) {
        with(context) {
            text("Test Group")
                if(checkbox("Check This", active)) {
                    active = !active
                }
                //ImGui.sliderInt("Is this slidin'", value, 0, 100)*/
            separator()
        }
    }
}