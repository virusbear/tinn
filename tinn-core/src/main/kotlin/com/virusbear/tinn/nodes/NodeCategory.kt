package com.virusbear.tinn.nodes

val ConstantNodeCategory =
    NodeCategory(
        parent = NodeCategory.Math,
        name = "Constant"
    )
val TrigonometryNodeCategory =
    NodeCategory(
        parent = NodeCategory.Math,
        name = "Trigonometry"
    )

val NodeCategory.Companion.Constant
    inline get() = ConstantNodeCategory
val NodeCategory.Companion.Trigonometry
    inline get() = TrigonometryNodeCategory