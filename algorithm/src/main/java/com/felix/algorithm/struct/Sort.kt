package com.felix.algorithm.struct

import com.sun.org.apache.xpath.internal.operations.Bool
import java.lang.StringBuilder
import java.util.*

class Sort {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val sorts = arrayOf(bobleSort, selectSort, quickSort)
            for (sort in sorts) {
                for (i in 0 until 6) {
                    validate(i, sort).takeUnless { it.success }?.let {
                        println(it.msg)
                    }
                }
                sort.javaClass.fields[0].type.simpleName.let {
                    it.substring(it.indexOfFirst { it == '$' } + 1,
                        it.indexOfLast { it == '$' })
                }.let {
                    println("$it success.")
                }
            }
        }

        fun validate(size: Int, sort: (IntArray) -> Unit): Result {
            val sortName = sort.javaClass.fields[0].type.simpleName.let {
                it.substring(it.indexOfFirst { it == '$' } + 1,
                    it.indexOfLast { it == '$' })
            }
            fullArray(size).forEach {
                val expect = it.string()

                sort.invoke(it)
                it.forEachIndexed { index, i ->
                    if (i != index + 1) {
                        return Result(false, "sortName=$sortName:$expect=>${it.string()}")
                    }
                }
            }
            return Result(true, "size=$size,sort=${sortName} success")
        }

        val bobleSort = { nums: IntArray ->
            for (i in 0 until nums.size) {
                for (j in 0 until nums.size - 1) {
                    if (nums[j] > nums[j + 1]) {
                        nums[j] = nums[j] xor nums[j + 1]
                        nums[j + 1] = nums[j] xor nums[j + 1]
                        nums[j] = nums[j] xor nums[j + 1]
                    }
                }
            }
        }
        val selectSort = { nums: IntArray ->
            var minIndex: Int
            for (i in 0 until nums.size) {
                minIndex = i
                for (j in i + 1 until nums.size) {
                    if (nums[j] < nums[minIndex]) {
                        minIndex = j
                    }
                }
                if (i != minIndex) {
                    nums[i] = nums[i] xor nums[minIndex]
                    nums[minIndex] = nums[i] xor nums[minIndex]
                    nums[i] = nums[i] xor nums[minIndex]
                }
            }
        }

        val quickSort = { nums: IntArray ->
            quictSort(nums, 0, nums.size - 1)
        }

        fun quictSort(nums: IntArray, start: Int, end: Int) {
            if (start >= end) {
                return
            }
            var i = start
            var j = end
            while (j > i) {
                while (j > i && nums[j] >= nums[start]) j--
                while (j > i && nums[i] <= nums[start]) i++
                if (i != j) {
                    nums[i]=nums[i] xor nums[j]
                    nums[j]=nums[i] xor nums[j]
                    nums[i]=nums[i] xor nums[j]
                }
            }
            if (i != start) {
                nums[i] = nums[i] xor nums[start]
                nums[start] = nums[i] xor nums[start]
                nums[i] = nums[i] xor nums[start]
            }
            quictSort(nums, start, i - 1)
            quictSort(nums, i + 1, end)
        }

        fun fullArray(size: Int): List<IntArray> {
            return mutableListOf<Int>().also {
                for (i in 0 until size) {
                    it.add(i + 1)
                }
            }.let {
                fullArray(it)
            }.map {
                it.toIntArray()
            }
        }

        fun fullArray(nums: MutableList<Int>): MutableList<MutableList<Int>> {
            if (nums.size == 1) {
                return mutableListOf(nums)
            }
            val result = mutableListOf<MutableList<Int>>()
            for (num in nums) {
                fullArray(nums.filter { it != num }.toMutableList())
                    .also {
                        it.forEach {
                            it.add(0, num)
                        }
                    }.let {
                        result.addAll(it)
                    }
            }
            return result
        }
    }
}

data class Result(var success: Boolean, var msg: String)

fun IntArray.string(): String {
    if (isEmpty()) {
        return "[]"
    }
    val sb = StringBuilder()
    sb.append('[')
    sb.append(get(0))
    for (i in 1 until size) {
        sb.append(",").append(get(i))
    }
    sb.append(']')
    return sb.toString()
}