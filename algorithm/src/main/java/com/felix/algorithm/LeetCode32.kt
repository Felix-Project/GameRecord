package com.felix.algorithm

import com.felix.algorithm.ext.printListLn
import java.util.*

class LeetCode32 {
    companion object {
        fun longestValidParentheses(s: String): Int {
            val stack = Stack<Char>()
            var max = 0
            var tmpMax = 0
            val length = s.length
            for (i in 0 until length) {
                if (s[i] != '(') {
                    continue
                }
                for (j in i until length step 2) {
                    if (s[i] == '(' && s[i + 1] == ')') {
//                        te
                    }
                }
            }
            s.forEachIndexed { index, c ->
                stack.clear()
                tmpMax = 0
                for (i in index until s.length) {
                    if (s[i] == '(') {
                        stack.push(s[i])
                        tmpMax++
                    } else if (s[i] == ')') {
                        if (stack.empty()) {
                            break
                        } else {
                            stack.pop()
                            tmpMax++
                        }
                    }
                }
                if (stack.empty()) {
                    if (max < tmpMax) {
                        max = tmpMax
                    }
                }
            }
            return max
        }

        @JvmStatic
        fun main(args: Array<String>) {
            println(longestValidParentheses(")()())"))
            println(longestValidParentheses("(()"))
        }
    }
}