package com.virusbear.tinn.nodes.colorbuffer

import com.virusbear.tinn.ColorBuffer
import com.virusbear.tinn.ColorFormat
import com.virusbear.tinn.Driver
import com.virusbear.tinn.nodes.BaseNode
import com.virusbear.tinn.nodes.NodeCategory
import com.virusbear.tinn.nodes.NodeIdentifier
import com.virusbear.tinn.nodes.Register
import java.io.File

class LoadImageNode: BaseNode("Load Image", LoadImageNode) {
    @Register
    companion object: NodeIdentifier("Load Image", NodeCategory.Utility, { LoadImageNode() })

    val file: File? by input("File", default = null)
    val format: ColorFormat by input("Color Format", default = ColorFormat.RGB8)
    var output: ColorBuffer? by output("Output", default = null)

    override fun process() {
        if(file == null) {
            output = null
            return
        }

        ColorBufferPool[file!!.absolutePath]?.let {
            output = it
        } ?: run {
            if(file == null)
                return@run

            output = Driver.driver.loadImage(file!!, format).also {
                ColorBufferPool[file!!.absolutePath] = it
            }
        }
    }
}