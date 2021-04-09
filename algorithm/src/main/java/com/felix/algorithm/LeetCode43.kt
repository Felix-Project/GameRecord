package com.felix.algorithm

import java.util.*

class LeetCode43 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(multiply("2", "3"))
        }

        fun multiply(num1: String, num2: String): String {
            val size2 = num2.length
            val size1 = num1.length
            val result = arrayOfNulls<LinkedList<Int>>(size2)
            for (i in 0 until size2) {
                result[i] = LinkedList<Int>()
            }
            for (i in 0 until size2) {
                for (k in i + 1 until size2) {
                    result[k]?.offer(0)
                }
                val tmp = num2[size2 - i - 1] - '0'
                var flag = 0
                for (j in size1 - 1 downTo 0) {
                    val r = tmp * (num1[j] - '0') + flag
                    flag = r / 10
                    result[i]?.offer(r % 10)
                }
                if (flag > 0) {
                    result[i]?.offer(flag)
                }
            }
            var flag = 0
            var sum = 0
            val stack = Stack<Int>()
            var nullNum = 0
            do {
                nullNum = 0
                sum = flag
                for (i in 0 until size2) {
                    val poll = result[i]?.poll()
                    if (poll == null) {
                        nullNum++
                    } else {
                        sum += poll ?: 0
                    }
                }
                flag = sum / 10
                stack.push(sum % 10)
            } while (nullNum < size2)
            val sb = StringBuilder()
            while (!stack.isEmpty()) {
                if (stack.peek() != 0) {
                    break
                } else {
                    stack.pop()
                }
            }
            while (!stack.isEmpty()) {
                sb.append(stack.pop())
            }
            if (sb.isEmpty()) {
                return "0"
            }
            return sb.toString()
        }
    }
}