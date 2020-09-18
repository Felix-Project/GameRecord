package com.felix.lib.algorithm

class TestCls2 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

//            kotlin.run {
//                val l1 = ListNode(1).apply {
//                    next = ListNode(4).apply {
//                        next = ListNode(5)
//                    }
//                }
//
//                val l2 = ListNode(1).apply {
//                    next = ListNode(3).apply {
//                        next = ListNode(4)
//                    }
//                }
//                val l3 = ListNode(2).apply {
//                    next = ListNode(6)
//                }
//                mergeKLists(arrayOf(l1, l2, l3)).let {
//                    it.print()
//                }
//            }

            kotlin.run {
                val l1 = ListNode(-2).apply {
                    next = ListNode(1)
                        .apply {
                        next = ListNode(
                            4
                        ).apply {
                            next =
                                ListNode(
                                    5
                                )
                        }
                    }
                }

                val l2 = ListNode(-2).apply {
                    next = ListNode(5)
                        .apply {
                        next =
                            ListNode(6)
                    }
                }
                val l3 = ListNode(-2).apply {
                    next = ListNode(0)
                }
                mergeKLists(
                    arrayOf(
                        l1,
                        l2,
                        l3
                    )
                ).let {
                    it?.print()
                }
            }


        }

        fun mergeKLists(lists: Array<ListNode?>): ListNode? {
            lists.filterNotNull().let {

            }
            var l: Node? = null
            var lp = l
            lists.forEach {
                if (it != null) {
                    if (lp == null) {
                        lp = Node(it)
                    } else {
                        lp?.next =
                            Node(it)
                        lp = lp?.next
                    }
                    if (l == null) {
                        l = lp
                    }
                }
            }
            if (l == null) {
                return null
            }
            var pHead: ListNode? = null
            var p: ListNode? = null
            while (l != null) {
                var n = l
                var minNode: Node? = n
                var minPre = minNode
                var min = n?.data?.`val`
                var nPre = n
                while (n != null) {
                    if (n?.data?.`val` ?: 0 < min ?: 0) {
                        minPre = nPre
                        minNode = n
                        min = minNode?.data?.`val`
                    }
                    nPre = n
                    n = n?.next
                }
                if (p == null) {
                    p = minNode?.data
                } else {
                    p?.next = minNode?.data
                    p = p?.next
                }
                minNode?.data = minNode?.data?.next
                p?.next = null
                if (minNode?.data == null) {
                    if (minNode == minPre) {
                        l = l?.next
                    } else {
                        minPre?.next = minPre?.next?.next
                    }
                }
                if (pHead == null) {
                    pHead = p
                }

            }
            return pHead
        }

        class ListNode(var `val`: Int) {
            var next: ListNode? = null

            fun print() {
                if (this == null) {
                    println("null")
                }
                var p: ListNode? = this
                while (p != null) {
                    print("   ${p?.`val`}")
                    p = p.next
                }
                println()
            }
        }

        class Node(var data: ListNode?) {
            var next: Node? = null
        }
    }
}

