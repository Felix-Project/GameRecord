package com.felix.lib.algorithm

class SortCls {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var a = 100
            var b = a
            a = a xor b
            b = a xor b
            a = a xor b


            a = 999
            a = a xor a
            a = a xor a
            a = a xor a


            println("b=$b,a=$a")
            "3".let {
                val result = mutableListOf<Int>()
                it.forEach {
                    result.add(it.toInt() - '0'.toInt())
                }
                result
            }.let {
                it.toIntArray()
            }.let {
                it.print()
                heapSort(it)
                it.print()
            }
        }

        fun heapSort(nums: IntArray, length: Int = nums.size - 1) {
            //构建大顶堆
            for (i in length downTo 0) {
                var parent = (i - 1) shr 1
                if (parent < 0 || nums[i] <= nums[parent]) {
                    continue
                }
                nums[i] = nums[i] xor nums[parent]
                nums[parent] = nums[i] xor nums[parent]
                nums[i] = nums[i] xor nums[parent]
                var k = i
                while (k < length) {
                    var kLeft = (k shl 1) + 1
                    var kRight = (k shl 1) + 2
                    if (kLeft > length) {
                        break
                    }
                    if (nums[k] < nums[kLeft] || kRight <= length && nums[k] < nums[kRight]) {
                        val maxIndex =
                            if (nums[kLeft] >= nums[kRight]) kLeft else kRight
                        nums[k] = nums[k] xor nums[maxIndex]
                        nums[maxIndex] = nums[k] xor nums[maxIndex]
                        nums[k] = nums[k] xor nums[maxIndex]
                        k = maxIndex
                        continue
                    }
                    break
                }
            }
            for (length in nums.size - 1 downTo 1) {
                nums[0] = nums[0] xor nums[length]
                nums[length] = nums[0] xor nums[length]
                nums[0] = nums[0] xor nums[length]

                var k = 0
                while (k < length) {
                    var kLeft = (k shl 1) + 1
                    var kRight = (k shl 1) + 2
                    if (kLeft >= length) {
                        break
                    }
                    if (nums[k] < nums[kLeft] || kRight < length && nums[k] < nums[kRight]) {
                        val maxIndex =
                            if (kRight >= length || nums[kLeft] >= nums[kRight]) kLeft else kRight
                        nums[k] = nums[k] xor nums[maxIndex]
                        nums[maxIndex] = nums[k] xor nums[maxIndex]
                        nums[k] = nums[k] xor nums[maxIndex]
                        k = maxIndex
                        continue
                    }
                    break
                }
            }
        }

        fun quickSort(list: List<Int>): List<Int> = if (list.size <= 1) list else {
            list.takeLast(list.lastIndex).partition {
                it < list[0]
            }.run {
                quickSort(first) + list[0] + quickSort(
                    second
                )
            }
        }

        fun bubble(nums: IntArray) {
            var change = false
            for (i in 0 until nums.size) {
                change = false
                for (j in 0 until nums.size - 1) {
                    if (nums[j] > nums[j + 1]) {
                        change = true
                        nums[j] = nums[j] xor nums[j + 1]
                        nums[j + 1] = nums[j] xor nums[j + 1]
                        nums[j] = nums[j] xor nums[j + 1]
                    }
                }
                if (!change) {
                    return
                }
            }
        }

        fun selectSort(nums: IntArray) {
            for (i in 0 until nums.size) {
                var index = i
                var min = nums[index]
                for (j in i + 1 until nums.size) {
                    if (nums[j] < min) {
                        index = j
                        min = nums[j]
                    }
                }
                if (index == i) {
                    continue
                }
                nums[i] = nums[i] xor nums[index]
                nums[index] = nums[i] xor nums[index]
                nums[i] = nums[i] xor nums[index]
            }
        }

        fun mergeSort(nums: IntArray, start: Int = 0, end: Int = nums.size - 1) {
            if (end - start <= 0) {
                return
            }
            if (end - start == 1) {
                if (nums[start] > nums[end]) {
                    nums[start] = nums[start] xor nums[end]
                    nums[end] = nums[start] xor nums[end]
                    nums[start] = nums[start] xor nums[end]
                }
                return
            }
            val medium = start + ((end - start) shr 1)
            mergeSort(
                nums,
                start,
                medium
            )
            mergeSort(
                nums,
                medium + 1,
                end
            )
            val newNums = IntArray(end - start + 1)
            var i = start
            var j = medium + 1
            for (k in 0 until newNums.size) {
                if (i > medium) {
                    newNums[k] = nums[j++]
                } else if (j > end) {
                    newNums[k] = nums[i++]
                } else if (nums[i] >= nums[j]) {
                    newNums[k] = nums[j++]
                } else {
                    newNums[k] = nums[i++]
                }
            }
            newNums.forEachIndexed { index, i ->
                nums[start + index] = i
            }
        }

        fun quickSort(nums: IntArray, start: Int = 0, end: Int = nums.size - 1) {
            if (end <= start) {
                return
            }
            var i = start
            var j = end
            while (i < j) {
                while (nums[j] >= nums[start] && i < j) j--
                while (nums[i] <= nums[start] && i < j) i++
                if (i != j) {
                    nums[i] = nums[i] xor nums[j]
                    nums[j] = nums[i] xor nums[j]
                    nums[i] = nums[i] xor nums[j]
                }
            }
            if (i != start) {
                nums[i] = nums[i] xor nums[start]
                nums[start] = nums[i] xor nums[start]
                nums[i] = nums[i] xor nums[start]
            }
            quickSort(
                nums,
                start,
                i - 1
            )
            quickSort(nums, i + 1, end)
        }
    }
}

fun IntArray.print() {
    if (this.size <= 0) {
        println("[]")
    }
    print("[${this[0]}")
    for (i in 1 until size) {
        print(",${this[i]}")
    }
    println("]")

}