package com.felix.lib

class TestCls123 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            //找出字符串的最长 回文串
            println(findMax("abccbawrere"))
            println(findMax("abccba"))
            println(findMax("abcvelgoogleul"))


        }

        fun findMax(s: String): String {
            var minIndex = 0
            var maxIndex = 0
            for (i in 0 until s.length) {
                for (j in i + 1 until s.length) {
                    if (s[j] == s[i]) {
                        //判断是否回文
                        var k = 0
                        var flag = true   //标记是否回文
                        while (i + k < j - k) {
                            if (s[i + k] != s[j - k]) {
                                //非回文
                                flag = false
                                break
                            }
                            k++
                        }
                        if(flag){
                            if(j-i>maxIndex-minIndex){
                                maxIndex=j
                                minIndex=i
                            }
                        }
                    }
                }

            }
            return s.substring(minIndex,maxIndex+1)
        }

    }
}