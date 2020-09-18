package com.felix.lib.algorithm

class TestCls6 {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            letterCombinations("23").let {
                it.forEach {
                    println(it)
                }
            }
        }
        val map=listOf<CharArray>(charArrayOf('a','b','c'),
            charArrayOf('d','e','f'),
            charArrayOf('g','h','i'),
            charArrayOf('j','k','l'),
            charArrayOf('m','n','o'),
            charArrayOf('p','q','r','s'),
            charArrayOf('t','u','v'),
            charArrayOf('w','x','y','z'))
        fun letterCombinations(digits: String): List<String> {
            if(digits.length==0){
                return emptyList<String>()
            }
            return letterBind(digits).map{
                val sb=StringBuilder()
                it.forEach{
                    sb.append(it)
                }
                sb.toString()
            }
        }
        fun letterBind(digits:String,start:Int=0):List<List<Char>>{
            val curArray=digits[start].let{
                it.toInt()-'2'.toInt()
            }.let{
                map[it].toList()
            }.let{
                it.toList()
            }
            if(start>=digits.length-1){
                return curArray.map {
                    listOf(it)
                }
            }
            val nextArray= letterBind(
                digits,
                start + 1
            )
            val result=mutableListOf<List<Char>>()
            curArray.forEach{cur->
                nextArray.forEach{nextList->
                    nextList.toMutableList().apply{
                        add(0,cur)
                    }.let{
                        result.add(it)
                    }
                }
            }
            return result
        }
    }
}