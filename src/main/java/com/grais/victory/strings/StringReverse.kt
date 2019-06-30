package com.grais.victory.strings

fun main(args: Array<String>) {
    println("Please, enter a string")
    val text = readLine() ?: ""
    val reversed = reverseString(text)
    println("Reversed string is $reversed")
}

fun reverseString(text: String): String {
    val charArray = text.toCharArray()
    var i = 0
    var j = charArray.size - 1
    while (i < j) {
        val temp = text[i]
        charArray[i] = text[j]
        charArray[j] = temp
        i++
        j--
    }
    return String(charArray)
}