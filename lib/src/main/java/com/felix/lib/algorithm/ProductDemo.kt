package com.felix.lib.algorithm

import java.util.concurrent.Executors
import java.util.concurrent.locks.ReentrantLock

class ProductDemo {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            for (i in 0 until ProductNum) {
                Executors.newCachedThreadPool().execute(Producer())
            }
            for (i in 0 until ConsumeNum) {
                Executors.newCachedThreadPool().execute(Consumer())
            }
        }

        val lock = ReentrantLock()
        val notFull = lock.newCondition()
        val notEmpty = lock.newCondition()
        const val MAX = 10
        const val ConsumeNum = 5
        const val ProductNum = 8
        var count = 0

        class Producer : Runnable {
            override fun run() {
                lock.lock()
                while (count == MAX) {
                    notFull.await()
                }
                try {
                    count++
                    notEmpty.signal()
                } finally {
                    lock.unlock()
                }
            }
        }

        class Consumer : Runnable {
            override fun run() {
                lock.lock()
                while (count == 0) {
                    notEmpty.await()
                }
                try {
                    count--
                    notFull.signal()
                } finally {
                    lock.unlock()
                }
            }
        }
    }
}