package com.felix.algorithm

import com.felix.algorithm.ext.printListln

class LeetCode4 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            LeetCode4().let {
                it.combinationSum3(3, 7).printListln()
                it.combinationSum3(3, 9).printListln()
            }
        }
    }

    fun combinationSum3(k: Int, n: Int): List<List<Int>> {
        val result = mutableListOf(ListHolder())
        val first = if (n >= 9) 9 else n
        for (i in first downTo 1) {
            val resultCopy = mutableListOf<ListHolder>()
            result.forEach { listHolder ->
                listHolder.takeIf { it.size < k }?.takeIf {
                    val allSum = it.sum + i
                    allSum < n || allSum == n && it.size == k - 1
                }?.let {
                    resultCopy.add(it.copy().also { copy ->
                        copy.list.add(i)
                        copy.sum += i
                        copy.size++
                    })
                }
            }
            result.addAll(resultCopy)
        }
        return result.filter {
            it.sum == n && it.size == k
        }.map {
            it.list
        }
    }

    class ListHolder {
        val list = mutableListOf<Int>()
        var sum = 0
        var size = 0
        fun copy() = ListHolder().also {
            it.list.addAll(this.list)
            it.sum = this.sum
            it.size = this.size
        }
    }

}