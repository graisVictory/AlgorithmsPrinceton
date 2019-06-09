package com.grais.victory.algorithms.unionfind

interface IUnionFind {

    fun union(first: Int, second: Int)

    fun find(element: Int): Int
}