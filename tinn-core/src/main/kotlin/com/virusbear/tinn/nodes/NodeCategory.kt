package com.virusbear.tinn.nodes

val ConstantNodeCategory =
    NodeCategory(
        parent = NodeCategory.Math,
        name = "Constant"
    )
val NodeCategory.Companion.Constant
    inline get() = ConstantNodeCategory