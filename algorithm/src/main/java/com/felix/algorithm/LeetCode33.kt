package com.felix.algorithm

class LeetCode33 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            LeetCode33().apply {
                println(search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 0))
                println(search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 3))
                println(search(intArrayOf(1), 0))
                println(search(intArrayOf(1, 3), 0))
            }
        }
    }

    fun search(nums: IntArray, target: Int): Int {
        val min = findMin(nums)
        val size = nums.size
        println("search $min")
        return search2(nums, target, min, min + size)
    }

    fun findMin(nums: IntArray): Int {
        val size = nums.size
        var end = size - 1
        var start = 0
        var mid: Int
        do {
            mid = (start + end) shr 1
            if (nums[start] < nums[end]) {
                return start
            } else if (nums[mid] < nums[end]) {
                end = mid
            } else {
                start = mid + 1
            }
        } while (end > start)
        return end
    }

    fun search2(nums: IntArray, target: Int, startIndex: Int = 0, endIndex: Int = nums.size): Int {
        val size = nums.size
        var start = startIndex
        var end = endIndex
        var mid: Int
        do {
            mid = (start + end) shr 1
            if (nums[mid % size] == target) {
                return mid % size
            } else if (nums[mid % size] > target) {
                end = mid - 1
            } else {
                start = mid + 1
            }
        } while (end > start)
        if (nums[start%size] == target) {
            return start%size
        }
        return -1
    }
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        val row=arrayOfNulls<HashMap<Char,Boolean>> (9)
        val col=arrayOfNulls<HashMap<Char,Boolean>> (9)
        val box=arrayOfNulls<HashMap<Char,Boolean>> (9)
        for(i in 0..8){
            row[i]=hashMapOf()
            col[i]=hashMapOf()
            box[i]=hashMapOf()
        }
        for(i in 0..8){
            for(j in 0..8){
                val boxIndex=(i/3)*3+j
                val num=board[i][j]
                if(row[i]?.get(num)==true ){
                    return false
                }else{
                    row[i]?.put(num,true)
                }
                if(col[j]?.get(num)==true ){
                    return false
                }else{
                    col[j]?.put(num,true)
                }
                if(box[boxIndex]?.get(num)==true ){
                    return false
                }else{
                    box[boxIndex]?.put(num,true)
                }
            }
        }
        return true
    }
}