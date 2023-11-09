package com.virusbear.tinn.nodes.colorbuffer

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.ProcessingContext
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import java.io.File

class SaveImageNode: BaseNode("Save Image", SaveImageNode) {
    companion object: NodeIdentifier("Save Image", NodeCategory.Utility, factory = { SaveImageNode() })

    val file: File? by input("File", default = null)
    val image: ColorBuffer? by input("Image", default = null)

    override fun process(context: ProcessingContext) {
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