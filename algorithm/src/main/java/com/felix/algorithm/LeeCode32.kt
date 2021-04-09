package com.felix.algorithm

import java.util.*

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/2/16
 * @Des: LeeCode32
 */
class LeeCode32 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            LeeCode32().apply {
                arrayOf("(())()(()((", ")()())", "(())(", "()(()", "").forEach { s ->
                    longestValidParentheses(s).also {
                        print(s)
                        print("=>")
                        println(it)
                    }
                }


            }
        }
    }

    fun longestValidParentheses(s: String): Int {
        val stack = Stack<Boolean>()
        var max = 0
        var tmpMax = 0
        var tmp = 0
        var currentIndex = 0
        var index = 0
        while (index < s.length) {
            stack.clear()
            tmpMax = 0
            tmp = 0
            currentIndex = index
            for (i in index until s.length) {
                if (s[i] == '(') {
                    stack.push(true)
                    tmpMax++
                } else if (s[i] == ')') {
                    if (stack.empty()) {
                        break
                    } else {
                        stack.pop()
                        tmpMax++
                        if (stack.isEmpty()) {
                            tmp = tmpMax
                            currentIndex = i
                        }
                    }
                }
            }
            if (max < tmp) {
                max = tmp
            }
            index = currentIndex + 1
        }
        return max
    }

}