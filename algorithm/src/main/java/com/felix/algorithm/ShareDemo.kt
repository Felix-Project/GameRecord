package com.felix.algorithm

//import org.apache.poi.ss.formula.functions.T

class ShareDemo {
    var next: ShareDemo? = null


    companion object {
        var sPools: ShareDemo? = null
        var size = 0
        const val MAX_SIZE = 10
        val lock = Any()

    }

    fun obtain(): ShareDemo {
        synchronized(lock) {
            var result = sPools
            sPools = sPools?.next
            size--
            return result ?: ShareDemo()
        }
    }

    fun recycler() {
        reset()
        synchronized(lock) {
            if (size < MAX_SIZE) {
                next = sPools
                sPools = this
                size++
            }
        }
    }

    protected fun reset() {

    }
}