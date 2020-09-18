package com.felix.lib.algorithm

class TestCls5 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            1.let {
                it+200
            }.let {
                String.format("")
            }
        }

        fun findSubsequences(nums: IntArray): List<List<Int>> {
            if (nums.size <= 1) {
                return emptyList()
            }
            nums.sort()
            return findSeq(nums, 0)
                .let { map ->
                map.keys.sorted().mapNotNull {
                    map[it]
                }.filter {
                    it.size >= 2
                }
            }
//            return findSeq(nums, 0).values.filter {
//                it.size >= 2
//            }.toList()
        }

        fun findSeq(nums: IntArray, start: Int): Map<String, List<Int>> {
            val map = hashMapOf<String, List<Int>>()
            if (start >= nums.size - 1) {
                map[nums[start].toString()] = listOf(nums[start])
                return map
            }
            val preMap = findSeq(
                nums,
                start + 1
            )
            preMap.forEach { (k, v) ->
                val value = v.toMutableList().apply {
                    add(0, nums[start])
                }
                val key = nums[start].toString() + k
                map[key] = value
            }
            map.putAll(preMap)
            return map
        }
    }
}