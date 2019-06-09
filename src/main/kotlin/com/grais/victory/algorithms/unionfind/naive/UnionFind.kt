package com.grais.victory.algorithms.unionfind.naive

import com.grais.victory.algorithms.unionfind.IUnionFind

class UnionFind(capacity: Int): IUnionFind {

    private val dataSet = IntArray(capacity) { it }

    override fun union(first: Int, second: Int) {
        check(first in dataSet.indices)
        check(second in dataSet.indices)

        val firstRoot = find(first)
        val secondRoot = find(second)
        dataSet[firstRoot] = secondRoot
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