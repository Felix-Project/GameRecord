package com.felix.lib.algorithm

import java.util.*

class TestCls9 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

        }

        /**
         * Example:
         * var ti = TreeNode(5)
         * var v = ti.`val`
         * Definition for a binary tree node.
         * class TreeNode(var `val`: Int) {
         *     var left: TreeNode? = null
         *     var right: TreeNode? = null
         * }
         */
        class Solution {
            fun levelOrder(root: TreeNode?): IntArray {
                val queue = LinkedList<TreeNode>()
                root?.let {
                    queue.offer(root)
                } ?: run {
                    return intArrayOf()
                }
                val result = mutableListOf<Int>()
                while (!queue.isEmpty()) {
                    val e = queue.poll()
                    e?.left?.let {
                        queue.offer(it)
                    }
                    e?.also {
                        result.add(it.`val`)
                    }?.let {
                        it.left?.let {
                            queue.offer(it)
                        }
                        it.right?.let {
                            queue.offer(it)
                        }
                    }
                }
                return result.toIntArray()
            }
        }
    }
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}