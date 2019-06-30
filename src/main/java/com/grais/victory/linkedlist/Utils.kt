package com.grais.victory.linkedlist

fun fillListWithInt(count: Int): Node<Int> {
    var first = Node(0)
    var node: Node<Int>? = first
    for (i in 1 until count) {
        node?.next = Node(i)
        node = node?.next
    }
    return first
}

fun <T> printList(first: Node<T>) {
    var node: Node<T>? = first
    while (node != null) {
        print("${node.value} ")
        node = node.next
    }
}