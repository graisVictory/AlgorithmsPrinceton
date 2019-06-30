package com.grais.victory.linkedlist

fun main(args: Array<String>) {
    val first = fillListWithInt(10)

    println("------ List ------")
    printList(first)

    println("Please, specify k")

    val k = readLine()?.toIntOrNull() ?: -1
    if (k == -1) {
        throw IllegalArgumentException("Incorrect k specified")
    }
    val kElement = findKElement(first, k)
    print("$k element from the end is ${kElement?.value}")
}

private fun <T> findKElement(first: Node<T>, k: Int): Node<T>? {
    var p1: Node<T>? = first
    var p2: Node<T>? = first
    var i = 0
    while (i < k && p1 != null) {
        i++
        p1 = p1.next
    }
    if (i < k) {
        return null
    }
    while (p1 != null) {
        p1 = p1.next
        p2 = p2?.next
    }
    return p2
}