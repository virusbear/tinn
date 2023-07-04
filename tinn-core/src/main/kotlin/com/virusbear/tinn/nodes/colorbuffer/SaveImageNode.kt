package com.virusbear.tinn.nodes.colorbuffer

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.nodes.*
import com.virusbear.tinn.Context
import com.virusbear.tinn.registry.Register
import java.io.File

class SaveImageNode: BaseNode("Save Image", SaveImageNode) {
    @Register("tinn:save-image")
    companion object: NodeIdentifier("Save Image", NodeCategory.Utility, factory = { SaveImageNode() })

    val file: File? by input("File", default = null)
    val image: ColorBuffer? by input("Image", default = null)

    override fun process(context: Context) {
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