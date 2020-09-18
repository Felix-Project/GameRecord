package com.felix.lib.algorithm

import com.felix.lib.algorithm.ext.println
import java.util.*

class LeetCode5 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val root = TreeNode(1)
            root.right = TreeNode(2).apply {
                left = TreeNode(3)
            }
            inorderTraversal(root).println()

        }

        fun inorderTraversal(root: TreeNode?): List<Int> {
            val result = mutableListOf<Int>()
            // inorderTraversal(root,result)

            var stack = Stack<TreeNode>()
            root?.let {
                stack.push(it)
            }
            while (stack.isNotEmpty()) {
                stack.peek().left?.let {
                    stack.peek().left = null
                    stack.push(it)
                } ?: run {
                    stack.pop().let {
                        result.add(it.`val`)
                        it.right
                    }?.let {
                        stack.push(it)
                    }
                }
            }
            return result
        }
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}