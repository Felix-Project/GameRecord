package com.felix.lib.bean

import com.felix.lib.game.fromJson
import com.felix.lib.game.toJson
import java.lang.StringBuilder

class ScoreLine {
    lateinit var gameMap: GameMap
    var pro: Float = 0f
    var one: Float = 0f
    var two: Float = 0f
    var three: Float = 0f
    var entertainment: Float = 0f

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            "[{\"gameMap\":{\"id\":1,\"name\":\"城镇高速公路\",\"score\":144.15},\"pro\":1.41,\"one\":1.42,\"two\":1.44,\"three\":1.47,\"entertainment\":1.5},{\"gameMap\":{\"id\":2,\"name\":\"城镇南山\",\"score\":141.05},\"pro\":1.37,\"one\":1.39,\"two\":1.41,\"three\":1.44,\"entertainment\":1.47},{\"gameMap\":{\"id\":3,\"name\":\"滨海风景\",\"score\":156.91},\"pro\":1.54,\"one\":1.55,\"two\":1.57,\"three\":2.0,\"entertainment\":2.03},{\"gameMap\":{\"id\":4,\"name\":\"狮城狂飙\",\"score\":207.2},\"pro\":2.03,\"one\":2.05,\"two\":2.07,\"three\":2.1,\"entertainment\":2.13},{\"gameMap\":{\"id\":5,\"name\":\"惊险飞机场\",\"score\":231.94},\"pro\":2.26,\"one\":2.28,\"two\":2.3,\"three\":2.32,\"entertainment\":2.35},{\"gameMap\":{\"id\":9,\"name\":\"迪拜富人区\",\"score\":151.65},\"pro\":1.46,\"one\":1.48,\"two\":1.5,\"three\":1.53,\"entertainment\":1.57},{\"gameMap\":{\"id\":8,\"name\":\"灵岩环道\",\"score\":152.73},\"pro\":1.49,\"one\":1.51,\"two\":1.53,\"three\":1.56,\"entertainment\":1.59},{\"gameMap\":{\"id\":6,\"name\":\"日光城\",\"score\":154.27},\"pro\":1.5,\"one\":1.52,\"two\":1.55,\"three\":1.58,\"entertainment\":2.02},{\"gameMap\":{\"id\":11,\"name\":\"野外修炼馆\",\"score\":136.59},\"pro\":1.33,\"one\":1.35,\"two\":1.38,\"three\":1.42,\"entertainment\":1.46},{\"gameMap\":{\"id\":10,\"name\":\"城镇手指\",\"score\":127.81},\"pro\":1.2,\"one\":1.22,\"two\":1.25,\"three\":1.28,\"entertainment\":1.32},{\"gameMap\":{\"id\":13,\"name\":\"恐龙决斗场\",\"score\":159.41},\"pro\":1.53,\"one\":1.55,\"two\":1.58,\"three\":2.01,\"entertainment\":2.04},{\"gameMap\":{\"id\":12,\"name\":\"里约滑坡\",\"score\":113.11},\"pro\":1.1,\"one\":1.12,\"two\":1.14,\"three\":1.16,\"entertainment\":1.19},{\"gameMap\":{\"id\":14,\"name\":\"浮空要塞\",\"score\":140.73},\"pro\":1.32,\"one\":1.24,\"two\":1.37,\"three\":1.4,\"entertainment\":1.44},{\"gameMap\":{\"id\":15,\"name\":\"遗忘孤城\",\"score\":202.43},\"pro\":1.56,\"one\":1.58,\"two\":2.01,\"three\":2.04,\"entertainment\":2.08},{\"gameMap\":{\"id\":21,\"name\":\"万里长城\",\"score\":151.7},\"pro\":1.45,\"one\":1.47,\"two\":1.5,\"three\":1.54,\"entertainment\":1.58},{\"gameMap\":{\"id\":22,\"name\":\"海盗绝壁海湾\",\"score\":202.33},\"pro\":1.56,\"one\":1.58,\"two\":2.02,\"three\":2.06,\"entertainment\":2.1},{\"gameMap\":{\"id\":23,\"name\":\"海底神殿\",\"score\":228.27},\"pro\":2.21,\"one\":2.24,\"two\":2.28,\"three\":2.32,\"entertainment\":2.37},{\"gameMap\":{\"id\":16,\"name\":\"城镇命运之桥\",\"score\":214.35},\"pro\":2.06,\"one\":2.08,\"two\":2.12,\"three\":2.16,\"entertainment\":2.2},{\"gameMap\":{\"id\":17,\"name\":\"龙之路\",\"score\":106.42},\"pro\":1.04,\"one\":1.06,\"two\":1.08,\"three\":1.11,\"entertainment\":1.14},{\"gameMap\":{\"id\":20,\"name\":\"龙之遗迹\",\"score\":148.41},\"pro\":1.42,\"one\":1.44,\"two\":1.48,\"three\":1.52,\"entertainment\":1.56},{\"gameMap\":{\"id\":30,\"name\":\"城镇公路\",\"score\":116.48},\"pro\":1.1,\"one\":1.12,\"two\":1.15,\"three\":1.18,\"entertainment\":1.22},{\"gameMap\":{\"id\":27,\"name\":\"大城堡秘密地下室\",\"score\":210.73},\"pro\":2.01,\"one\":2.04,\"two\":2.08,\"three\":2.13,\"entertainment\":2.18},{\"gameMap\":{\"id\":28,\"name\":\"黄山赛道\",\"score\":115.31},\"pro\":1.1,\"one\":1.12,\"two\":1.14,\"three\":1.17,\"entertainment\":1.2},{\"gameMap\":{\"id\":29,\"name\":\"海盗宝藏迷宫\",\"score\":211.87},\"pro\":2.04,\"one\":2.07,\"two\":2.11,\"three\":2.15,\"entertainment\":2.2},{\"gameMap\":{\"id\":24,\"name\":\"森林发夹\",\"score\":220.82},\"pro\":2.14,\"one\":2.17,\"two\":2.2,\"three\":2.25,\"entertainment\":2.3},{\"gameMap\":{\"id\":26,\"name\":\"沙漠旋转工地\",\"score\":205.41},\"pro\":1.57,\"one\":2.0,\"two\":2.04,\"three\":2.08,\"entertainment\":2.13},{\"gameMap\":{\"id\":25,\"name\":\"英特拉格斯\",\"score\":155.79},\"pro\":1.49,\"one\":1.51,\"two\":1.53,\"three\":1.56,\"entertainment\":2.0},{\"gameMap\":{\"id\":37,\"name\":\"矿山曲折滑坡\",\"score\":122.96},\"pro\":1.13,\"one\":1.16,\"two\":1.2,\"three\":1.25,\"entertainment\":1.3},{\"gameMap\":{\"id\":36,\"name\":\"太空蜿蜒跑道\",\"score\":201.31},\"pro\":1.54,\"one\":1.57,\"two\":2.01,\"three\":2.05,\"entertainment\":2.1},{\"gameMap\":{\"id\":38,\"name\":\"未完成的第五区\",\"score\":123.2},\"pro\":1.18,\"one\":1.2,\"two\":1.23,\"three\":1.27,\"entertainment\":1.32},{\"gameMap\":{\"id\":35,\"name\":\"彩虹之门\",\"score\":211.49},\"pro\":1.59,\"one\":2.02,\"two\":2.06,\"three\":2.11,\"entertainment\":2.16},{\"gameMap\":{\"id\":32,\"name\":\"秘密机关的威胁\",\"score\":201.55},\"pro\":1.54,\"one\":1.56,\"two\":2.0,\"three\":2.05,\"entertainment\":2.1},{\"gameMap\":{\"id\":33,\"name\":\"冰峰裂谷\",\"score\":145.75},\"pro\":1.35,\"one\":1.37,\"two\":1.4,\"three\":1.43,\"entertainment\":1.47},{\"gameMap\":{\"id\":39,\"name\":\"森林崎岖下山道\",\"score\":149.24},\"pro\":1.36,\"one\":1.39,\"two\":1.42,\"three\":1.46,\"entertainment\":1.52},{\"gameMap\":{\"id\":40,\"name\":\"幽暗峡谷\",\"score\":201.67},\"pro\":1.51,\"one\":1.54,\"two\":1.57,\"three\":2.01,\"entertainment\":2.05}]".let {
                it.fromJson<List<ScoreLine>>()
            }.let {
                println(it.toFormatterJson())
            }
        }
    }
}

fun Float.toFormatString() = String.format("%.2f", this)

fun List<ScoreLine>.toFormatterJson() = this.toJson()
    .replace("{", "{\n")
    .replace("[", "[\n")
    .replace(",", ",\n")
    .replace("}", "\n}")
    .replace("]", "\n]").let {
        val sb = StringBuilder()
        var tabNum = 0
        var index = 0
        while (index < it.length) {
            val size = if (it[index] in arrayOf(']', '}')) tabNum - 1 else tabNum
            for (i in 0 until size) {
                sb.append("\t")
            }
            while (index < it.length && it[index] != '\n') {
                if (it[index] in arrayOf('[', '{')) {
                    tabNum++
                } else if (it[index] in arrayOf(']', '}')) {
                    tabNum--
                }
                sb.append(it[index])
                index++
            }
            if (index < it.length) {
                sb.append(it[index])
            }
            index++
        }
        sb.toString()
    }


