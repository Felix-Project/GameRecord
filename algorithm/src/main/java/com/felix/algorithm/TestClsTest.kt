package com.felix.algorithm

class TestClsTest {
    companion object {
        val DoubleZero = 0.00001

        @JvmStatic
        fun main(args: Array<String>) {
            judgePoint241(
                intArrayOf(1, 5, 9, 1)
            ).let {
                println(it)
            }
            intArrayOf(4, 1, 8, 7).let {
                judgePoint241(it)
            }.let {
                println(it)
            }
        }

        fun judgePoint241(nums: IntArray): Boolean {
            val target = 24.0
            return nums.map { it.toDouble() }.let {
                solve(it, target)
            }
        }

        fun solve(nums: List<Double>, target: Double): Boolean {
            val size = nums.size
            if (size == 0) {
                return false
            }
            if (size == 1) {
                return Math.abs(nums[0] - target) <= DoubleZero
            }
            for (i in 0 until size - 1) {
                for (j in i + 1 until size) {
                    val toSolve = nums.filterIndexed { index, d ->
                        index != i && index != j
                    }
                    (nums[i] + nums[j]).let {
                        toSolve.toMutableList().apply { add(it) }
                    }.takeIf {
                        solve(
                            it,
                            target
                        )
                    }?.let {
                        return true
                    }

                    (nums[i] - nums[j]).let {
                        toSolve.toMutableList().apply { add(it) }
                    }.takeIf {
                        solve(
                            it,
                            target
                        )
                    }?.let {
                        return true
                    }
                    (nums[j] - nums[i]).let {
                        toSolve.toMutableList().apply { add(it) }
                    }.takeIf {
                        solve(
                            it,
                            target
                        )
                    }?.let {
                        return true
                    }

                    (nums[i] * nums[j]).let {
                        toSolve.toMutableList().apply { add(it) }
                    }.takeIf {
                        solve(
                            it,
                            target
                        )
                    }?.let {
                        return true
                    }

                    nums[i].takeIf { it != 0.0 }?.let {
                        nums[j] / it
                    }?.let {
                        toSolve.toMutableList().apply { add(it) }
                    }?.takeIf {
                        solve(
                            it,
                            target
                        )
                    }?.let {
                        return true
                    }

                    nums[j].takeIf { it != 0.0 }?.let {
                        nums[i] / it
                    }?.let {
                        toSolve.toMutableList().apply { add(it) }
                    }?.takeIf {
                        solve(
                            it,
                            target
                        )
                    }?.let {
                        return true
                    }
                }
            }
            return false
        }


        fun judgePoint24(nums: IntArray): Boolean {
            //0 1
            val target = 24.0
            if (matchTwo(
                    nums,
                    0,
                    1,
                    2,
                    3,
                    target
                )
            ) {
                return true
            }
            if (matchTwo(
                    nums,
                    0,
                    2,
                    1,
                    3,
                    target
                )
            ) {
                return true
            }
            if (matchTwo(
                    nums,
                    0,
                    3,
                    1,
                    2,
                    target
                )
            ) {
                return true
            }
            return canToTarget(
                nums.toList(),
                target
            )
        }

        fun matchTwo(
            nums: IntArray,
            index0: Int,
            index1: Int,
            index2: Int,
            index3: Int,
            target: Double
        ): Boolean {
            val result1 =
                getResultList(
                    nums[index0],
                    nums[index1]
                )
            val result2 =
                getResultList(
                    nums[index2],
                    nums[index3]
                )
            result1.forEach { data1 ->
                result2.forEach { data2 ->
                    if (Math.abs(data1 + data2 - target) <= DoubleZero) {
                        return true
                    }
                    if (Math.abs(data1 - data2 - target) <= DoubleZero) {
                        return true
                    }
                    if (Math.abs(data2 - data1 - target) <= DoubleZero) {
                        return true
                    }
                    if (Math.abs(data1 * data2 - target) <= DoubleZero) {
                        return true
                    }
                    if (Math.abs(data1 / data2 - target) <= DoubleZero) {
                        return true
                    }
                    if (Math.abs(data2 / data1 - target) <= DoubleZero) {
                        return true
                    }
                }
            }
            return false
        }

        fun getResultList(num1: Int, num2: Int): List<Double> {
            val result = mutableListOf<Double>()
            result.add(num1.toDouble() + num2)
            result.add(num1.toDouble() - num2)
            result.add(num2.toDouble() - num1)
            result.add(num1.toDouble() * num2)
            result.add(num1.toDouble() / num2)
            result.add(num2.toDouble() / num1)
            return result
        }

        fun canToTarget(nums: List<Int>, target: Double): Boolean {
            if (nums.size == 0) {
                return false
            }
            if (nums.size == 1) {
                return Math.abs(target - nums[0].toDouble()) <= DoubleZero
            }
            if (nums.size == 2) {
                if (Math.abs(nums[0] + nums[1] - target) <= DoubleZero) {
                    return true
                }
                if (Math.abs(nums[0] - nums[1] - target) <= DoubleZero) {
                    return true
                }
                if (Math.abs(nums[1] - nums[0] - target) <= DoubleZero) {
                    return true
                }
                if (Math.abs(nums[0] * nums[1] - target) <= DoubleZero) {
                    return true
                }
                if (Math.abs(nums[0].toDouble() / nums[1] - target) <= DoubleZero) {
                    return true
                }
                if (Math.abs(nums[1].toDouble() / nums[0] - target) <= DoubleZero) {
                    return true
                }
                return false
            }
            nums.forEachIndexed { index, data ->
                val next = nums.filterIndexed { i, d ->
                    i != index
                }
                if (canToTarget(
                        next,
                        target / data
                    )
                ) {
                    return true
                }
                if (canToTarget(
                        next,
                        data / target
                    )
                ) {
                    return true
                }
                if (canToTarget(
                        next,
                        target - data
                    )
                ) {
                    return true
                }
                if (canToTarget(
                        next,
                        data - target
                    )
                ) {
                    return true
                }
            }
            return false
        }
    }
}