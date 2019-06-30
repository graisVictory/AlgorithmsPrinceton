package com.grais.victory.linkedlist

fun main(args: Array<String>) {
    var first = fillListWithInt(10)

    println("-------- Before -------")
    printList(first)

    first = reverseList(first)

    println()
    println("-------- After -------")
    printList(first)
}

private fun <T> reverseList(first: Node<T>): Node<T> {
    var previous = first
    var current = previous.next
    previous.next = null
    while (current != null) {
        val nextCurrent = current.next
        current.next = previous
        previous = current
        current = nextCurrent
    }
    return previous
}