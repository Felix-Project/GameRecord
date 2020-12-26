package com.felix.algorithm

import java.util.*

class testCls7 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val listOf = listOf(listOf("hehe"))
            val link = LinkedList(listOf)
        }

        fun findItinerary(tickets: List<List<String>>): List<String> {
            val link = LinkedList(tickets)
            var curTicket = "JFK"
            val result = mutableListOf(curTicket)
            var node: List<String>? = null
            while (link.isNotEmpty()) {
                node = null
                link.iterator().let {
                    while (it.hasNext()) {
                        it.next().let { sList ->
                            if (sList[0] == curTicket) {
                                node?.let {
                                    if (sList[1] < it[1]) {
                                        node = sList
                                    }
                                } ?: kotlin.run {
                                    node = sList
                                }
                            }
                        }
                    }
                }
                if(node==null){
                    return emptyList()
                }
                node?.let {
                    link.remove(it)
                    result.add(it[1])
                    curTicket=it[1]
                }
            }
            return result
        }

    }

}