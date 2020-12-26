package com.felix.algorithm

import com.felix.algorithm.ext.printListLn

class LeetCode31 {
    companion object {

        fun nextPermutation(nums: IntArray): Unit {
            val size = nums.size
            for (i in size - 1 downTo 0) {
                var temp = Int.MAX_VALUE
                var index = -1
                for (j in i + 1 until size) {
                    if (nums[j] > nums[i] && nums[j] < temp) {
                        temp = nums[j]
                        index = j
                    }
                }
                if (index > 0) {
                    nums[i] = nums[i] xor nums[index]
                    nums[index] = nums[i] xor nums[index]
                    nums[i] = nums[i] xor nums[index]

                    nums.sort(i + 1)
                    return
                }
            }

            var half = (size shr 1) - 1
            for (i in 0..half) {
                nums[i] = nums[i] xor nums[size - 1 - i]
                nums[size - 1 - i] = nums[i] xor nums[size - 1 - i]
                nums[i] = nums[i] xor nums[size - 1 - i]
            }
        }

        @JvmStatic
        fun main(args: Array<String>) {
            var nums = intArrayOf(3, 2, 1)
            nums = intArrayOf(1, 3, 2)
            nums = intArrayOf(4, 3, 2,1)
            nums.printListLn()
            nextPermutation(nums)
            nums.printListLn()
        }
    }
}