package com.grais.victory.algorithms.unionfind.weighted

import com.grais.victory.algorithms.unionfind.IUnionFind

class WeightedUnionFind(capacity: Int): IUnionFind {

    private val dataSet = IntArray(capacity) { it }
    private val weights = IntArray(capacity) { 1 }

    override fun union(first: Int, second: Int) {
        check(first in dataSet.indices)
        check(second in dataSet.indices)

        val firstRoot = find(first)
        val secondRoot = find(second)
        val firstWeight = weights[firstRoot]
        val secondWeight = weights[secondRoot]

        if (firstWeight > secondWeight) {
            dataSet[secondRoot] = firstRoot
            weights[firstRoot] += secondWeight
        } else {
            dataSet[firstRoot] = secondRoot
            weights[secondRoot] += firstWeight
        }
    }

    override fun find(element: Int): Int {
        check(element in dataSet.indices)

        val root = dataSet[element]
        if (root != element) {
            return find(root)
        }
        return root
    }

    override fun toString(): String {
        return dataSet.contentToString()
    }
}