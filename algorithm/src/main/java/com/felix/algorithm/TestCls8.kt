package com.felix.algorithm

import java.lang.ref.ReferenceQueue
import java.lang.ref.WeakReference
import java.util.concurrent.Executors

class TestCls8 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val referenceQueue = ReferenceQueue<String>()
            val softReferencc = WeakReference<String>("abc", referenceQueue)
            softReferencc.let {
                println(it.get())
            }
            System.gc()
            System.gc()
            System.gc()
            System.gc()

            softReferencc.let {
                println("====${it.get()}====")
            }

            Executors.newCachedThreadPool().execute {
                while (true){
                    referenceQueue.poll()?.let {
                        println(it)
                    }
                }
            }
            Thread.sleep(20000)
        }
    }
}