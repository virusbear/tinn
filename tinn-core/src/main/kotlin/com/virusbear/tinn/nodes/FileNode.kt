package com.virusbear.tinn.nodes

import java.io.File

class FileNode: BaseNode("File") {
    @Register
    companion object: NodeIdentifier("File", NodeCategory.Utility, ::FileNode)

    val path: String by input("Path", default = "")
    var file: File? by output("Output", default = null)

    override fun process() {
        file = File(path)
    }
}