package com.virusbear.tinn.ui.compose.modifier

interface ParentDataModifier: Modifier.Element {
    fun modifyParentData(parentData: Any?): Any?
}