package com.felix.lib.bean

import java.lang.StringBuilder

class GameMap {
    companion object {
        fun defGameMap() = GameMap().apply {
            id = -1
            name = "unknow"
            score = 0f
        }
    }

    var id: Int = 0
    var name: String = ""
    var score: Float = 0f
    var topScore: Float = 0f
    var classic = false
    var classicLevel = 10
    fun getScoreResult(): String {
        return score.takeIf { it >= 100 }?.let {
            it.toFormatString()
        }?.let {
            StringBuilder()
                .append(it[0])
                .append(".")
                .append(it.substring(1))
                .toString()
        } ?: score.toFormatString()
    }

    fun getTopResult(): String {
        return topScore.takeIf { it >= 100 }?.let {
            it.toFormatString()
        }?.let {
            StringBuilder()
                .append(it[0])
                .append(".")
                .append(it.substring(1))
                .toString()
        } ?: topScore.toFormatString()
    }

//    fun getShortScoreResult() {
//        return score.toFormatString()
//            .let {
//                StringBuilder()
//                    .append(it[0])
//                    .append(".")
//                    .append(it.substring(1))
//            }
//    }
}