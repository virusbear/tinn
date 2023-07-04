package com.virusbear.tinn.nodes

import com.virusbear.tinn.Context
import com.virusbear.tinn.registry.Register
import java.io.File

class FileNode: BaseNode("File", FileNode) {
    @Register("tinn:file")
    companion object: NodeIdentifier("File", NodeCategory.Utility, factory = { FileNode() })

    val path: String by input("Path", default = "")
    var file: File? by output("Output", default = null)

    override fun process(context: Context) {
        file = File(path)
    }
}