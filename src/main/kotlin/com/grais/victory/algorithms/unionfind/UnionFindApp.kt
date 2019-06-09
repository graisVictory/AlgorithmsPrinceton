package com.grais.victory.algorithms.unionfind

import com.grais.victory.algorithms.unionfind.naive.UnionFind
import com.grais.victory.algorithms.unionfind.weighted.WeightedUnionFind

private val algorithms = mapOf(
        Pair("simple", { capacity: Int -> UnionFind(capacity) }),
        Pair("weighted", { capacity: Int -> WeightedUnionFind(capacity) })
)

fun main(args: Array<String>) {
    println("Choose algorithm: ${algorithms.keys}")

    val algorithmName = readLine()?.trim()

    check(algorithms.keys.contains(algorithmName))

    println("Please enter capacity")

    val capacity = readLine()?.toInt()
    check(capacity != null && capacity > 0)

    val algorithm = algorithms[algorithmName]!!(capacity!!)

    println("Please enter the unions")

    var union = readLine()
    while (!union.isNullOrBlank()) {
        val values = union!!.split(",")
        if (values.size < 2) {
            break
        }
        val first = values[0].toInt()
        val second = values[1].toInt()

        algorithm.union(first, second)
        println(algorithm)

        union = readLine()
    }
}