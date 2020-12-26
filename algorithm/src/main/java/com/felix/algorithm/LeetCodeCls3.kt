package com.felix.algorithm

import com.felix.algorithm.ext.println

class LeetCodeCls3 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val leetCodeCls3 = LeetCodeCls3()
            "barfoothefoobarman".let {
                leetCodeCls3.findSubstring(it, arrayOf("foo", "bar")).let {
                    it.println()
                }
            }
            "wordgoodgoodgoodbestword".let {
                leetCodeCls3.findSubstring(it, arrayOf("word", "good", "best", "word")).let {
                    it.println()
                }
            }
            "wordgoodgoodgoodbestword".let {
                leetCodeCls3.findSubstring(it, arrayOf("word", "good", "best", "good")).let {
                    it.println()
                }
            }
            "aaaa".let {
                leetCodeCls3.findSubstring(it, arrayOf("a", "a", "a", "a")).let {
                    it.println()
                }
            }
        }
    }


    fun findSubstring(s: String, words: Array<String>): List<Int> {
        val result = mutableListOf<Int>()
        val subSize = words.size * words.first().length
        var index = 0
        val maxSize = s.length - subSize
        while (index <= maxSize) {
            if (match(s, index, words.toMutableList(), mutableListOf())) {
                result.add(index)
                index += subSize
            } else {
                index++
            }
        }
        return result
    }

    fun match(
        s: String,
        index: Int,
        toMatchList: MutableList<String>,
        unMatchList: MutableList<String>
    ): Boolean {
        val size = toMatchList.first().length
        if (index + size > s.length) {
            return false
        }
        for (i in 0 until size) {
            val iterator = toMatchList.iterator()
            while (iterator.hasNext()) {
                val next = iterator.next()
                if (next[i] != s[index + i]) {
                    unMatchList.add(next)
                    iterator.remove()
                }
            }
        }
        if (toMatchList.isEmpty()) {
            return false
        }
        toMatchList.removeAt(0)
        toMatchList.addAll(unMatchList)
        unMatchList.clear()
        if (toMatchList.size == 0) {
            return true
        }
        return match(s, index + size, toMatchList, unMatchList)
    }
}


