package com.felix.lib.game.main

import com.felix.lib.bean.GameMap
import com.felix.lib.game.fromJson
import com.felix.lib.game.toJson

class GameMapTools {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            "[{\"id\":1,\"name\":\"城镇运河\",\"score\":109.39,\"topScore\":106.01},{\"id\":2,\"name\":\"城镇高速公路\",\"score\":143.79,\"topScore\":137.87},{\"id\":3,\"name\":\"城镇南山\",\"score\":141.05,\"topScore\":133.9},{\"id\":4,\"name\":\"滨海风景\",\"score\":156.91,\"topScore\":150.77},{\"id\":5,\"name\":\"龙之都市\",\"score\":131.54,\"topScore\":125.16},{\"id\":6,\"name\":\"东方明珠之夜\",\"score\":134.3,\"topScore\":126.26},{\"id\":7,\"name\":\"狮城狂飙\",\"score\":207.2,\"topScore\":159.56},{\"id\":8,\"name\":\"惊险飞机场\",\"score\":230.76,\"topScore\":223.26},{\"id\":9,\"name\":\"日光城\",\"score\":154.18,\"topScore\":145.81},{\"id\":10,\"name\":\"龙之宫殿\",\"score\":129.23,\"topScore\":121.44},{\"id\":11,\"name\":\"龙之运河\",\"score\":213.5,\"topScore\":202.06},{\"id\":12,\"name\":\"恐龙坟墓\",\"score\":146.37,\"topScore\":141.58},{\"id\":13,\"name\":\"灵岩环道\",\"score\":152.24,\"topScore\":146.69},{\"id\":14,\"name\":\"赛道9\",\"score\":47.24,\"topScore\":42.28},{\"id\":15,\"name\":\"迪拜富人区\",\"score\":150.51,\"topScore\":143.32},{\"id\":16,\"name\":\"巴黎风情\",\"score\":134.21,\"topScore\":125.24},{\"id\":17,\"name\":\"滨海火车\",\"score\":154.46,\"topScore\":142.53},{\"id\":18,\"name\":\"野外修炼馆\",\"score\":136.59,\"topScore\":129.66},{\"id\":19,\"name\":\"城镇手指\",\"score\":127.81,\"topScore\":117.17},{\"id\":20,\"name\":\"里约滑坡\",\"score\":111.86,\"topScore\":107.84},{\"id\":21,\"name\":\"360塔楼\",\"score\":133.23,\"topScore\":124.82},{\"id\":22,\"name\":\"恐龙决斗场\",\"score\":159.3,\"topScore\":149.02},{\"id\":23,\"name\":\"浮空要塞\",\"score\":138.3,\"topScore\":129.68},{\"id\":24,\"name\":\"遗忘孤城\",\"score\":159.46,\"topScore\":151.7},{\"id\":25,\"name\":\"城镇命运之桥\",\"score\":212.76,\"topScore\":200.3},{\"id\":26,\"name\":\"龙之路\",\"score\":106.42,\"topScore\":100.8},{\"id\":27,\"name\":\"神秘研究所\",\"score\":212,\"topScore\":203.4},{\"id\":28,\"name\":\"玩具工厂\",\"score\":115.74,\"topScore\":107.73},{\"id\":29,\"name\":\"龙之冰灯祭\",\"score\":126.62,\"topScore\":115.67},{\"id\":30,\"name\":\"铃鹿赛道\",\"score\":154.6,\"topScore\":146.77},{\"id\":31,\"name\":\"太空军用机场\",\"score\":201.97,\"topScore\":148.58},{\"id\":32,\"name\":\"城镇高速公路(反向)\",\"score\":151.43,\"topScore\":139.82},{\"id\":33,\"name\":\"城镇极限竞技场\",\"score\":150.91,\"topScore\":137.41},{\"id\":34,\"name\":\"巴黎铁塔\",\"score\":207.4,\"topScore\":157.95},{\"id\":35,\"name\":\"意大利比萨斜塔\",\"score\":109.16,\"topScore\":100.9},{\"id\":36,\"name\":\"龙之西湖\",\"score\":159.38,\"topScore\":150.3},{\"id\":37,\"name\":\"龙之遗迹\",\"score\":144.77,\"topScore\":136.75},{\"id\":38,\"name\":\"万里长城\",\"score\":149.57,\"topScore\":139.84},{\"id\":39,\"name\":\"海盗绝壁海湾\",\"score\":200.93,\"topScore\":150.38},{\"id\":40,\"name\":\"海底神殿\",\"score\":228.27,\"topScore\":215.9},{\"id\":41,\"name\":\"纽约狂飙\",\"score\":128.17,\"topScore\":123.59},{\"id\":42,\"name\":\"森林发夹\",\"score\":220.82,\"topScore\":208.56},{\"id\":43,\"name\":\"英特拉格斯\",\"score\":155.06,\"topScore\":146.22},{\"id\":44,\"name\":\"沙漠旋转工地\",\"score\":203.57,\"topScore\":152.46},{\"id\":45,\"name\":\"大城堡秘密地下室\",\"score\":205.89,\"topScore\":156.26},{\"id\":46,\"name\":\"黄山赛道\",\"score\":115.31,\"topScore\":106.84},{\"id\":47,\"name\":\"海盗宝藏迷宫\",\"score\":211.87,\"topScore\":157.16},{\"id\":48,\"name\":\"城镇公路\",\"score\":116.48,\"topScore\":107.38},{\"id\":49,\"name\":\"冰峰裂谷\",\"score\":145.75,\"topScore\":131.51},{\"id\":50,\"name\":\"圣诞秘密空间\",\"score\":143.2,\"topScore\":134.62},{\"id\":51,\"name\":\"黄金坐标\",\"score\":141.4,\"topScore\":133.39},{\"id\":52,\"name\":\"秘密机关的威胁\",\"score\":201.55,\"topScore\":148.14},{\"id\":53,\"name\":\"街头狂飙\",\"score\":218.73,\"topScore\":204.24},{\"id\":53,\"name\":\"海盗峭壁瞭望塔\",\"score\":151.94,\"topScore\":142.45},{\"id\":54,\"name\":\"彩虹之门\",\"score\":208.39,\"topScore\":152.91},{\"id\":55,\"name\":\"太空蜿蜒跑道\",\"score\":201.31,\"topScore\":148.96},{\"id\":56,\"name\":\"矿山曲折滑坡\",\"score\":122.96,\"topScore\":109.99},{\"id\":57,\"name\":\"未完成的第五区\",\"score\":123.2,\"topScore\":114.6},{\"id\":58,\"name\":\"森林崎岖下山道\",\"score\":147.68,\"topScore\":131.26},{\"id\":59,\"name\":\"幽暗峡谷\",\"score\":158.85,\"topScore\":144.17},{\"id\":60,\"name\":\"冰山滑雪场\",\"score\":224.48,\"topScore\":210.18},{\"id\":61,\"name\":\"森林发夹(反向)\",\"score\":221.68,\"topScore\":206.68}]".let {
                GameMapTools().resetId(it)
            }.let {
                println(it)
            }
        }

    }

    fun resetId(str: String) =
        str.fromJson<List<GameMap>>().also {
            it.forEachIndexed { index, gameMap ->
                gameMap.id = index + 1
            }
        }.toJson()

}