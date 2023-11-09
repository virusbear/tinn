package com.virusbear.tinn.nodes

import com.virusbear.tinn.ProcessingContext
import java.io.File

class FileNode: BaseNode("File", FileNode) {
    companion object: NodeIdentifier("File", NodeCategory.Utility, factory = { FileNode() })

    val path: String by input("Path", default = "")
    var file: File? by output("Output", default = null)

    override fun process(context: ProcessingContext) {
        file = File(path)
    }
}