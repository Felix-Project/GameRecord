package com.felix.algorithm

import java.util.*

class LeetCodeCls {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
//            getPermutation(3, 3).let {
//                println(it)
//            }
//            getPermutation(4, 9).let {
//                println(it)
//            }
//            getPermutation(2, 2).let {
//                println(it)
//            }
//            getPermutation(3, 2).let {
//                println(it)
//            }
            (-2147483648 / -1).let {
                println(it)
            }
            divide(-100, 3).let {
                println(it)
            }
            divide(-2147483648, -1)
                .let {
                println(it)
            }
        }

        fun divide(dividend: Int, divisor: Int): Int {
            var dividend1: UInt = if (dividend > 0)
                dividend.toUInt()
            else (dividend.inv().toUInt()) + 1U
            var divisor1 = if (divisor > 0)
                divisor.toUInt()
            else (divisor.inv().toUInt()) + 1U

            if (dividend1 < divisor1) {
                return 0
            }
            var negative = false
            if (dividend < 0) {
                negative = negative.not()
            }
            if (divisor < 0) {
                negative = negative.not()
            }

            var bit = 0
            while (divisor1 != 0U) {
                divisor1 = divisor1 shr 1
                bit++
            }
            var result = 0U
            divisor1 = if (divisor > 0)
                divisor.toUInt()
            else (divisor.inv().toUInt()) + 1U
            for (i in (32 - bit) downTo 0) {
                val tmp = divisor1 shl i
                if (dividend1 >= tmp) {
                    result += 1U shl i
                    dividend1 -= tmp
                }
            }
            if (negative) {
                return result.inv().toInt() + 1
            }
            if (result == 0x80000000U) {
                return 0x7FFFFFFF
            }
            return result.toInt()
        }

        fun getPermutation(n: Int, k: Int): String {
            val sb = StringBuilder()
            val list = LinkedList<Int>()
            val stack = Stack<Int>()
            list.add(1)
            stack.push(1)
            for (i in 1 until n - 1) {
                list.offer(i + 1)
                stack.push(stack.peek() * (i + 1))
            }
            list.offer(n)

            var findIndex = k
            var top = 0
            for (i in (n - 2) downTo 0) {
                top = stack.pop()
                val index = (findIndex - 1) / top
                sb.append(list.removeAt(index))
                findIndex -= top * index
            }
            sb.append(list.poll())
            return sb.toString()
        }
        /*
        * 1234
        * 1243
        * 1324
        * 1342
        * 1423
        * 1432
        * 2134
        * 2143
        * 2314
        *
        *
        *
        * */

    }
}