package com.felix.algorithm

import com.felix.algorithm.ext.printListln

class LeetCodeCls2 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            intArrayOf(10, 1, 2, 7, 6, 1, 5).let {
                combinationSum2(
                    it,
                    8
                )
            }.let {
                it.printListln()
            }
            intArrayOf(2, 5, 2, 1, 2).let {
                combinationSum2(
                    it,
                    5
                )
            }.let {
                it.printListln()
            }

            intArrayOf(5, 3).let {
                combinationSum2(
                    it,
                    2
                )
            }.let {
                it.printListln()
            }
        }

        fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
            return candidates.also {
                it.sort()
            }.takeWhile { it <= target }.toIntArray().let {
                when {
                    it.isEmpty() -> emptyList()

                    it.size == 1 -> {
                        if (candidates[0] == target) {
                            listOf(listOf(candidates[0]))
                        } else {
                            emptyList()
                        }
                    }
                    else -> {
                        combination(
                            it,
                            0,
                            target
                        ).filter {
                            it.sum() == target
                        }
                    }
                }
            }
        }

        fun combination(nums: IntArray, index: Int, target: Int): List<List<Int>> {
            if (index == nums.size - 1) {
                return listOf(emptyList(), listOf(nums[index]))
            }
            val pre = combination(
                nums,
                index + 1,
                target
            )
            val result = mutableListOf<List<Int>>()
            result.addAll(pre)

            pre.map {
                it.toMutableList().also {
                    it.add(nums[index])
                }
            }.filter {
                it.sum() <= target
            }.filter { dataItem ->
                result.forEach { resultItem ->
                    if (dataItem.size != resultItem.size) {
                        return@forEach
                    }
                    resultItem.forEachIndexed { index, i ->
                        if (dataItem[index] != i) {
                            return@forEach
                        }
                    }
                    return@filter false
                }
                true
            }.let {
                result.addAll(it)
            }
            return result
        }
    }
}
