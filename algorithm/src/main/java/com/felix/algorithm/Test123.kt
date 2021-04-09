package com.felix.algorithm

class Test123 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            intArrayOf(3, 2, 4, 5, 6, 2, 7, 9).also {
                quictSort(it)
            }.also { list ->
                list.forEach {
                    println(it)
                }
            }
        }

        fun quictSort(nums: IntArray, start: Int = 0, end: Int = nums.size - 1) {
            if (end - start <= 1) {
                return
            }
            var i = start
            var j = end
            while (j > i) {
                while (nums[start] <= nums[j] && j > i) j--
                while (nums[start] >= nums[i] && j > i) i++
                if (i != j) {
                    nums[i] = nums[i] xor nums[j]
                    nums[j] = nums[i] xor nums[j]
                    nums[i] = nums[i] xor nums[j]
                }
            }
            if (start != i) {
                nums[start] = nums[start] xor nums[i]
                nums[i] = nums[start] xor nums[i]
                nums[start] = nums[start] xor nums[i]
            }
            quictSort(nums, start, i - 1)
            quictSort(nums, i + 1, end)
        }
    }
}