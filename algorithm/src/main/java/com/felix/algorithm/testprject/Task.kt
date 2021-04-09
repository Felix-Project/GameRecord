package com.felix.algorithm.testprject

import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock

class Task : Observable, Executeable, Runnable, Prioritable, Comparable<Prioritable> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val task1 = Task()
            val task2 = Task()
            val list = LinkedList<Task>()
            list.offer(task1)
            list.offer(task2)
            var modify = false
            do {
                list.forEach {
                    val nextPriority = it.priority - 1
                    for (prioritable in it.priorityList) {
                        if (prioritable.priority > nextPriority) {
                            prioritable.priority = nextPriority
                            modify = true
                        }
                    }
                }
            } while (modify)
            list.sort()
            val newFixedThreadPool = Executors.newFixedThreadPool(4)
            for (task in list) {
                newFixedThreadPool.execute(task)
            }
        }
    }

    override var priority: Int = 0
    val waitCount = AtomicInteger(0)
    val lock = ReentrantLock()
    val wait = lock.newCondition()
    val observables: MutableList<Observable> = mutableListOf()
    val priorityList = mutableListOf<Prioritable>()
    fun depencienceOn(task: Task) {
        task.addObservable(this)
        priorityList.add(task)
        waitCount.getAndIncrement()
    }

    fun addObservable(observable: Observable) {
        this.observables.add(observable)
    }

    override fun accept() {
        lock.lock()
        try {
            if (waitCount.decrementAndGet() == 0) {
                wait.signal()
            }
        } finally {
            lock.unlock()
        }
    }

    override fun compareTo(other: Prioritable): Int {
        return this.priority - other.priority
    }

    override fun run() {
        lock.lock()
        try {
            while (waitCount.get() != 0) {
                wait.await()
            }
        } finally {
            lock.unlock()
        }
        execute()
        for (observable in observables) {
            observable.accept()
        }
    }

    override fun execute() {

    }
}

interface Prioritable {
    var priority: Int
}

interface Executeable {
    fun execute()
}

interface Observable {
    fun accept()
}