package com.virusbear.tinn.nodes.colorbuffer

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.Register
import java.io.File

class SaveImageNode: BaseNode("Save Image") {
    @Register
    companion object: NodeIdentifier("Save Image", NodeCategory.Utility, ::SaveImageNode)

    val file: File? by input("File", default = null)
    val image: ColorBuffer? by input("Image", default = null)

    override fun process() {
        if(file == null) {
            return
        }
        if(image == null) {
            return
        }

        file?.let {
            image?.save(it)
        }
    }
}