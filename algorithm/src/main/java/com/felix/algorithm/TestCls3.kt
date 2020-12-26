package com.felix.algorithm

class TestCls3 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
//            repeatedSubstringPattern("abab").let {
//                println(it)
//            }
//            repeatedSubstringPattern("aba").let {
//                println(it)
//            }
//            repeatedSubstringPattern("abcabc").let {
//                println(it)
//            }
//            "aabaaba".let {
//                repeatedSubstringPattern(it)
//            }.let {
//                println(it)
//            }
            hashMapOf<String, Int>().apply {
                put("数学", 100)
                put("语文", 90)
                put("英语", 87)
            }.also { map ->
                val iterator = map.iterator()
                while (iterator.hasNext()) {
                    val it = iterator.next()
//                    map.put(it.key + "1", it.value + 10)
                }
//                map.values.filter { it. }
            }.also {
                it.forEach { t, u ->
                    println("$t->$u")
                }
            }

        }

        fun repeatedSubstringPattern(s: String): Boolean {
            val size = s.length
            var i = 1
            val end = (size) shr 1
            while (i <= end) {
                if (s[i] == s[0] && size % i == 0) {
                    var match = true
                    for (j in 1 until size - i) {
                        if (s[j] != s[j + i]) {
                            match = false
                            break
                        }
                    }
                    if (match) {
                        return true
                    }
                }
                i++
            }

            return false
        }
    }
}