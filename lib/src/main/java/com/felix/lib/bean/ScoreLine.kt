package com.felix.lib.bean

class ScoreLine {
    lateinit var gameMap: GameMap
    var pro: Float = 0f
    var one: Float = 0f
    var two: Float = 0f
    var three: Float = 0f
    var entertainment: Float = 0f
}

fun Float.toFormatString() = String.format("%.2f", this)