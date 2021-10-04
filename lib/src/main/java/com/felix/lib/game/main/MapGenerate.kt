package com.felix.lib.game.main

import com.felix.lib.bean.GameMap
import com.felix.lib.bean.ScoreLine
import com.felix.lib.bean.toFormatString
import com.felix.lib.bean.toFormatterJson
import com.felix.lib.game.fromJson
import com.felix.lib.game.style
import okio.buffer
import okio.sink
import okio.source
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.awt.Desktop
import java.io.File

class MapGenerate {
    val root = File("game")
    val titleMap: Map<String, Array<String>> =
        hashMapOf(Pair("S12", arrayOf("强主", "主力", "一线", "二线", "三线")))

    val offset = 0.0f

    companion object {
        const val LEVEL = 10

        @JvmStatic
        fun main(args: Array<String>) {
            MapGenerate().generate()
        }
    }

    fun generate() {
        File(root, "GameMap.txt").let {
            //读取文件
            it.inputStream().source().buffer().readString(Charsets.UTF_8)
        }.let {
            //转成数据对象
            it.fromJson<List<GameMap>>()
        }.also {
            it.forEach {
                it.score -= offset
            }
        }.let {
            //转成map存储
            hashMapOf<String, GameMap>().apply {
                it.forEach {
                    this.put(it.name, it)
                }
            }.also {
                println("read data success.")
            }
        }.let { gameMaps ->
            File(root, "score").listFiles().also {
                val regex = Regex("[a-zA-Z.]+")
                it.sortBy {
                    100 - it.name.replace(regex, "").runCatching {
                        this.toInt()
                    }.also {
                        it.exceptionOrNull()?.printStackTrace()
                    }.getOrDefault(-1)
                }
            }.map { file ->
                val name = file.name.let {
                    it.subSequence(0, it.lastIndexOf("."))
                }.toString()
                file.let {
                    it.inputStream().source().buffer().readString(Charsets.UTF_8)
                }.let {
                    it.fromJson<List<ScoreLine>>()
                }.map { scoreLine ->
                    //更新成绩
                    scoreLine.also {
                        it.gameMap = gameMaps.getOrDefault(it.gameMap.name, it.gameMap)
                    }
                }.also { scoreLine ->
                    file.outputStream().sink().buffer().let {
                        it.writeUtf8(scoreLine.sortedWith(Comparator { o1, o2 ->
                            o1.gameMap.id - o2.gameMap.id
                        }).toFormatterJson())
                        it.flush()
                    }
                }.let {
                    val normal = ScoreLineHolder().apply {
                        this.scoreLineList = it
                        this.name = name + "交流分数线"
                        titleMap.get(name)?.let {
                            this.titles = it
                        }
                    }.also {
                        println("${it.name} 初始化成功")
                    }
                    val classic = ScoreLineHolder().apply {
                        this.scoreLineList = it.filter { it.gameMap.classicLevel >= LEVEL }
                        this.name = "经典" + name + "交流分数线"
                        this.titles = normal.titles
                    }.also {
                        println("${it.name} 初始化成功")
                    }
                    arrayOf(normal, classic)
                    arrayOf(classic)
                }
            }.toMutableList().let { list ->
                val result = mutableListOf<ScoreLineHolder>()
                list.forEach { array ->
                    array.forEach {
                        result.add(it)
                    }
                }
                result
            }.also { list ->
                //国服记录
                arrayOf(4, 5, 6, 7).filter { it == 0 }.map { name ->
                    gameMaps.values.toList().sortedWith(Comparator { o1, o2 -> o1.id - o2.id })
                        .map {
                            ScoreLine().apply {
                                this.gameMap = it
                                val score = (it.topScore + 0.99f).toInt() / 100f
                                this.pro = (score * 100 + name).toInt() / 100f
                                if (this.pro - this.pro.toInt() >= 0.6f) {
                                    this.pro = this.pro + 1f - 0.6f
                                }
                                this.pro = (this.pro * 100 + 0.99f).toInt() / 100f

                                this.one = this.pro + 0.02f
                                if (this.one - this.one.toInt() >= 0.6f) {
                                    this.one = this.one + 1f - 0.6f
                                }
                                this.one = (this.one * 100 + 0.99f).toInt() / 100f

                                this.two = this.one + 0.02f
                                if (this.two - this.two.toInt() >= 0.6f) {
                                    this.two = this.two + 1f - 0.6f
                                }
                                this.two = (this.two * 100 + 0.99f).toInt() / 100f

                                this.three = this.two + 0.02f
                                if (this.three - this.three.toInt() >= 0.6f) {
                                    this.three = this.three + 1f - 0.6f
                                }
                                this.three = (this.three * 100 + 0.99f).toInt() / 100f


                                this.entertainment = this.three + 0.02f
                                if (this.entertainment - this.entertainment.toInt() >= 0.6f) {
                                    this.entertainment = this.entertainment + 1f - 0.6f
                                }
                                this.entertainment =
                                    (this.entertainment * 100 + 0.99f).toInt() / 100f
                            }
                        }.let {
                            ScoreLineHolder().apply {
                                this.scoreLineList = it
                                this.name = "${name}分目标"
                                this.titles = arrayOf("一线", "二线", "三线", "四线", "五线")
                            }
                        }.let {
                            println("${it.name} 初始化成功")
                            list.add(it)
                        }
                }
            }
        }.let { holders ->
            //创建excel文件
            XSSFWorkbook().apply {
                holders.forEach {
                    createSheet(this, it.scoreLineList, it.name, it.titles)
                    println("create ${it.name} success.")
                }
            }
        }.let { workBook ->
            //写入文件
            File(root, "result.xlsx").also {
                it.setWritable(true)
            }.also {
                with(it.outputStream()) {
                    workBook.write(this)
                    this.flush()
                }
            }
        }.also {
            it.setWritable(false)
        }.takeIf {
            Desktop.isDesktopSupported()
        }?.let {
            Desktop.getDesktop().open(it)
        } ?: kotlin.run {
            System.out.println("Desktop is not supported")
        }
    }

    fun createSheet(
        xssfWorkbook: XSSFWorkbook,
        scoreLineList: List<ScoreLine>,
        name: String,
        title: Array<String>
    ) {
        xssfWorkbook.createSheet(name).let { sheet ->
            val hasTitle = false
            val firstRowIndex = if (hasTitle) 2 else 1
            val headerRowIndex = if (hasTitle) 1 else 0
            val mapNameIndex = 1     //地图名列
            val scoreIndex = 7      //成绩列
            val topIndex = scoreIndex + 1      //国服列
            val levelIndex = topIndex + 1     //分数线等级列
            val factionIndex = levelIndex + 1      //分数列
            sheet.defaultColumnWidth = 5
            sheet.setColumnWidth(0, 4 * 256)
            sheet.setColumnWidth(mapNameIndex, 18 * 256)
            sheet.setColumnWidth(scoreIndex, 8 * 256)
            sheet.setColumnWidth(topIndex, 8 * 256)
            sheet.setColumnWidth(levelIndex, 10 * 256)
            sheet.defaultRowHeight = 2 * 256
            //设置标题
            xssfWorkbook.takeIf { hasTitle }?.createCellStyle()?.apply {
                xssfWorkbook.createFont().apply {
                    setFontHeight(256 * 2)
                    bold = true
                }.let {
                    setFont(it)
                }
                alignment = HorizontalAlignment.CENTER
                verticalAlignment = VerticalAlignment.CENTER
                setFillForegroundColor(IndexedColors.LIGHT_GREEN.index)
                fillPattern = FillPatternType.SOLID_FOREGROUND
            }?.let {
                sheet.createRow(0)
                    .also {
                        it.height = 256 * 4
                    }
                    .createCell(0).style(it).setCellValue(name)
            }?.also {
                sheet.addMergedRegion(CellRangeAddress(0, 0, 0, factionIndex))
            }
            //设置头部
            xssfWorkbook.createCellStyle().apply {
                xssfWorkbook.createFont().apply {
                    bold = true
                }.also {
                    setFont(it)
                }
                BorderStyle.THIN.let {
                    borderBottom = it
                    borderLeft = it
                    borderRight = it
                    borderTop = it
                }
                IndexedColors.GREY_40_PERCENT.index.let {
                    leftBorderColor = it
                    rightBorderColor = it
                    topBorderColor = it
                    bottomBorderColor = it
                }
                setFillForegroundColor(IndexedColors.LIGHT_YELLOW.index)
                fillPattern = FillPatternType.SOLID_FOREGROUND
                alignment = HorizontalAlignment.CENTER
                verticalAlignment = VerticalAlignment.CENTER
            }.let { style ->
                sheet.createRow(headerRowIndex)
                    .also {
                        it.height = 480
                    }.also {
                        val datas = mutableListOf<String>()
                        datas.addAll(arrayOf("id", "地图"))
                        datas.addAll(title)
                        datas.addAll(arrayOf("成绩", "国服", "等级", "分数"))
                        datas.forEachIndexed { index, s ->
                            it.createCell(index).style(style).setCellValue(s)
                        }
                    }
            }
            scoreLineList.forEachIndexed { rowIndex, scoreLine ->
                sheet.createRow(rowIndex + firstRowIndex)
                    .also { it.height = 400 }
                    .let { row ->
                        val cellStyle = xssfWorkbook.createCellStyle().apply {
                            xssfWorkbook.createFont().apply {
                                setFont(this)
                            }
                            verticalAlignment = VerticalAlignment.CENTER
                            alignment = HorizontalAlignment.CENTER
                            if (rowIndex % 2 == 0) {
                                setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.index)
                            } else {
                                setFillForegroundColor(IndexedColors.LIGHT_YELLOW.index)
                            }
                        }

                        fun getScoreInt(score: Float) = score
                            .let { it * 100 + 0.5 }
                            .let { it.toInt() }
                            .let { it % 100 + it / 100 * 60 }

                        val lines = arrayListOf(
                            scoreLine.pro,
                            scoreLine.one,
                            scoreLine.two,
                            scoreLine.three,
                            scoreLine.entertainment
                        )
                        val values = title
                        val colors = arrayListOf(
                            IndexedColors.LIGHT_GREEN,
                            IndexedColors.GREEN,
                            IndexedColors.LIGHT_CORNFLOWER_BLUE,
                            IndexedColors.CORNFLOWER_BLUE,
                            IndexedColors.LIGHT_YELLOW,
                            IndexedColors.YELLOW,
                            IndexedColors.LIGHT_ORANGE,
                            IndexedColors.ORANGE,
                            IndexedColors.LIGHT_BLUE,
                            IndexedColors.BLUE,
                            IndexedColors.GREY_80_PERCENT
                        )
                        val score = scoreLine.gameMap.score.toInt() / 100f
                        var lineIndex = 0
                        lines.forEachIndexed { index, fl ->
                            if (score <= fl) {
                                return@forEachIndexed
                            }
                            lineIndex = index + 1
                        }
                        val doteDiff = scoreLine.gameMap.score.let {
                            it - it.toInt()
                        }

                        lines[Math.min(
                            lineIndex,
                            lines.size - 1
                        )] * 100 - scoreLine.gameMap.score
                        val diff: Int =
                            Math.abs(
                                getScoreInt(
                                    lines[Math.min(
                                        lineIndex,
                                        lines.size - 1
                                    )]
                                ) - getScoreInt(score)
                            )
                        val max: Int
                        if (lineIndex <= 0 || lineIndex >= lines.size) {
                            max = 3
                        } else {
                            max =
                                getScoreInt(lines[lineIndex]) - getScoreInt(lines[lineIndex - 1])
                        }
                        val additional =
                            if (lineIndex >= lines.size) 10 - 10 * diff / max else 10 * diff / max
                        val value: String
                        val faction = 100 - lineIndex * 10 + additional
                        if (lineIndex >= lines.size) {
                            value = values[lines.size - 1] + "开外"
                        } else {
                            value =
                                values[lineIndex] + if (score == lines[lineIndex]) "勉强" else "以上"
                        }

                        val factionResult: Float = faction - doteDiff
                        val datas = arrayListOf(
                            scoreLine.gameMap.id.toString(),
                            scoreLine.gameMap.name,
                            scoreLine.pro.toFormatString(),
                            scoreLine.one.toFormatString(),
                            scoreLine.two.toFormatString(),
                            scoreLine.three.toFormatString(),
                            scoreLine.entertainment.toFormatString(),
                            scoreLine.gameMap.getScoreResult(),
                            scoreLine.gameMap.getTopResult(),
                            value,
                            factionResult.toFormatString()
                        )
                        datas.forEachIndexed { index, s ->
                            val style: CellStyle
                            if (index == mapNameIndex) {
                                style = xssfWorkbook.createCellStyle().also {
                                    it.cloneStyleFrom(cellStyle)
                                }.also {
                                    it.alignment = HorizontalAlignment.LEFT
                                }
                            } else if (index in arrayOf(scoreIndex, topIndex)) {
                                style = xssfWorkbook.createCellStyle().also {
                                    it.cloneStyleFrom(cellStyle)
                                    it.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.index)
                                }
                            } else if (index == lineIndex + 2) {
                                style = xssfWorkbook.createCellStyle().also {
                                    it.cloneStyleFrom(cellStyle)
                                    if (score == lines[lineIndex]) {
                                        it.setFillForegroundColor(IndexedColors.ORANGE.index)
                                    } else {
                                        it.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index)
                                    }

                                }
                            } else if (index == levelIndex) {
                                style = xssfWorkbook.createCellStyle().also {
                                    it.cloneStyleFrom(cellStyle)
                                    val colorIndex: Int
                                    if (lineIndex >= lines.size) {
                                        colorIndex = colors.size - 1
                                    } else if (score == lines[lineIndex]) {
                                        colorIndex = lineIndex * 2 + 1
                                    } else {
                                        colorIndex = lineIndex * 2
                                    }
                                    it.setFillForegroundColor(colors[colorIndex].index)
                                }
                            } else if (index == factionIndex) {
                                style = xssfWorkbook.createCellStyle().also {
                                    it.cloneStyleFrom(cellStyle)
                                    it.setFillForegroundColor(colors[0].index)

                                }
                            } else {
                                style = cellStyle
                            }
                            style.also { style ->

                                //                                it.fillBackgroundColor = IndexedColors.RED.index
                                BorderStyle.THIN.let {
                                    style.borderBottom = it
                                    style.borderLeft = it
                                    style.borderRight = it
                                    style.borderTop = it
                                }
                                IndexedColors.GREY_40_PERCENT.index.let {
                                    style.leftBorderColor = it
                                    style.rightBorderColor = it
                                    style.topBorderColor = it
                                    style.bottomBorderColor = it
                                }
                                style.fillPattern = FillPatternType.SOLID_FOREGROUND
                            }.let {
                                row.createCell(index).style(it).setCellValue(s)
                            }
                        }
                    }
            }
        }
    }
}

class ScoreLineHolder {
    lateinit var scoreLineList: List<ScoreLine>
    lateinit var name: String
    var titles: Array<String> = arrayOf(
        "主力",
        "一线",
        "二线",
        "三线",
        "娱乐"
    )
}