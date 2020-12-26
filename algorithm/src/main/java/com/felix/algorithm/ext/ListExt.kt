package com.felix.algorithm.ext


fun List<Int>.print() {
    if (this.isEmpty()) {
        print("[]")
        return
    }
    print("[${this[0]}")
    for (i in 1 until size) {
        print(",${this[i]}")
    }
    print("]")
}

fun List<Int>.println() {
    this.print()
    kotlin.io.println()
}

fun List<List<Int>>.printList() {
    if (this.isEmpty()) {
        print("[]")
        return
    }
    println("[")
    this[0].print()
    for (i in 1 until size) {
        println(",")
        this[i].print()
    }
    println()
    println("]")
}

fun List<List<Int>>.printListln() {
    this.printList()
    println()
}

fun IntArray.printList() {
    if (this.isEmpty()) {
        print("[]")
        return
    }
    print("[${this[0]}")
    for (i in 1 until size) {
        print(",${this[i]}")
    }
    print("]")
}

fun IntArray.printListLn() {
    this.printList()
    println()
}