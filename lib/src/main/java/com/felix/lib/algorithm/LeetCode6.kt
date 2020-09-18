package com.felix.lib.algorithm

class LeetCode6 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            LeetCode6().let {
                val list = mutableListOf<CharArray>()
                "53..7....".toCharArray()
                list.add("53..7....".toCharArray())
                list.add("6..195...".toCharArray())
                list.add(".98....6.".toCharArray())

                list.add("8...6...3".toCharArray())
                list.add("4..8.3..1".toCharArray())
                list.add("7...2...6".toCharArray())

                list.add(".6....28.".toCharArray())
                list.add("...419..5".toCharArray())
                list.add("....8..79".toCharArray())
                val array = Array(9) {
                    charArrayOf()
                }
                repeat(9) { index ->
                    array[index] = list[index]
                }

                it.solveSudoku(array)
            }
        }
    }

    fun solveSudoku(board: Array<CharArray>): Unit {
        val map = Array(81) { NodeHolder() }

    }

    class NodeHolder {
        var set = false
        val avaiableList = hashMapOf<Int, Int>()
        val unAvaiableList = hashMapOf<Int, Int>()
    }
}