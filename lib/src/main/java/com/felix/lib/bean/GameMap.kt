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
    fun getScoreResult(): String {
        return score.toFormatString()
            .let {
                StringBuilder()
                    .append(it[0])
                    .append(".")
                    .append(it.substring(1))
                    .toString()
            }
    }

    fun getTopResult(): String {
        return topScore.toFormatString()
            .let {
                StringBuilder()
                    .append(it[0])
                    .append(".")
                    .append(it.substring(1))
                    .toString()
            }
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