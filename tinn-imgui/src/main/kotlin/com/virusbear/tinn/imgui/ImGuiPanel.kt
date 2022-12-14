package com.virusbear.tinn.imgui

import com.virusbear.tinn.ui.Panel

interface ImGuiPanel: Panel {
    fun windowFlags(): Int
}