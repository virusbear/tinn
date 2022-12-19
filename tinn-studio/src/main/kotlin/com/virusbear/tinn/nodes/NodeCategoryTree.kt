package com.virusbear.tinn.nodes

class NodeCategoryTree {
    val root = Element(NodeCategory.Root)

    operator fun plusAssign(nodeIdentifier: NodeIdentifier) {
        val element = getElement(nodeIdentifier.category) ?: addElement(nodeIdentifier.category)
        element += nodeIdentifier
    }

    private fun addElement(nodeCategory: NodeCategory): Element {
        val parentElement = nodeCategory.parent?.let { parentCategory ->
            getElement(parentCategory) ?: addElement(parentCategory)
        } ?: root

        parentElement += Element(nodeCategory)

        return getElement(nodeCategory)!!
    }

    private fun getElement(nodeCategory: NodeCategory): Element? {
        if(nodeCategory == NodeCategory.Root) {
            return root
        }

        if(nodeCategory.parent == null) {
            return null
        }

        val parent = getElement(nodeCategory.parent) ?: return null
        return parent[nodeCategory]
    }

    fun walk(onCategory: (NodeCategory) -> Unit, onNode: (NodeIdentifier) -> Unit) {
        walk0(root, onCategory, onNode)
    }

    private fun walk0(element: Element, onCategory: (NodeCategory) -> Unit, onNode: (NodeIdentifier) -> Unit) {
        onCategory(element.category)

        element.children.toSortedMap { a, b -> a.name.compareTo(b.name) }.forEach { (_, element) ->
            walk0(element, onCategory, onNode)
        }

        element.nodes.toSortedSet { a, b -> a.name.compareTo(b.name) }.forEach { node ->
            onNode(node)
        }
    }

    class Element(
        val category: NodeCategory
    ) {
        val children = HashMap<NodeCategory, Element>()
        val nodes = mutableSetOf<NodeIdentifier>()

        operator fun contains(category: NodeCategory) =
            category in children.keys

        operator fun get(category: NodeCategory): Element? =
            children[category]

        operator fun plusAssign(nodeIdentifier: NodeIdentifier) {
            if(category != nodeIdentifier.category) return

            nodes += nodeIdentifier
        }

        operator fun plusAssign(element: Element) {
            if(element.category in children) return

            children[element.category] = element
        }
    }
}