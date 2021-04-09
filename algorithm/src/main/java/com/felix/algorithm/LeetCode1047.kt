package com.felix.algorithm

import java.util.*

class LeetCode1047 {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            println(removeDuplicates("abbaca"))
        }

        fun removeDuplicates(S: String): String {
            var list=LinkedList<Char>()
            var j=0
            while(j<S.length){
                if(j==S.length-1||S[j]!=S[j+1]){
                    list.add(S[j])
                }else{
                    j++
                }
                j++
            }
            var remove=false
            var tmp= LinkedList<Char>()
            do{
                remove=false
                var i=0
                while(i<list.size){
                    if(i==list.size-1||list[i]!=list[i+1]){
                        tmp.add(list[i])
                    }else{
                        i++
                        remove=true
                    }
                    i++
                }
                val t=tmp
                tmp=list
                list=t
                tmp.clear()
            }while(remove)
            val sb=StringBuilder()
            for(i in 0 until list.size){
                sb.append(list[i])
            }
            return sb.toString()
        }
    }
}