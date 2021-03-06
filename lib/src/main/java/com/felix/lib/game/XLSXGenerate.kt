package com.felix.lib.game

import com.felix.lib.bean.GameMap
import com.felix.lib.bean.ScoreLine
import com.felix.lib.bean.toFormatString
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.awt.Desktop
import java.io.File

class XLSXGenerate {
    companion object {
        val root = File("game")
        @JvmStatic
        fun main(args: Array<String>) {
            File(root, "data.xlsx").inputStream().let {
                XSSFWorkbook(it)
            }.let { workBook ->
                val gameMapList = workBook.getSheet("gameMap").let { sheet ->
                    val gameMapList = mutableListOf<GameMap>()
                    for (i in 0 until sheet.physicalNumberOfRows) {
                        val row = sheet.getRow(i)
                        GameMap().apply {
                            id = i
                            name = row.getCell(0).stringCellValue
                            score = row.getCell(1).let {
                                if (it.cellType == CellType.NUMERIC) {
                                    it.numericCellValue.toFloat()
                                } else {
                                    it.stringCellValue.toFloat()
                                }
                            }
                        }.also {
                            gameMapList.add(it)
                        }
                    }
                    gameMapList
                }
                XSSFWorkbook().also {
                    arrayOf("s1", "s3", "s4", "s5").forEach { name ->
                        createSheet(
                            it,
                            getScoreLineInSheet(
                                workBook,
                                name,
                                gameMapList
                            ),
                            "${name.toUpperCase()}分数交流线"
                        )
                    }
                }.let { workBook ->
                    workBook.getSheetAt(workBook.numberOfSheets-1).let {sheet->
                        sheet.printSetup

                    }

                    File(root, "result.xlsx").also {
                        it.outputStream().let {
                            workBook.write(it)
                            it.flush()
                        }
                    }.also {
                        if (!Desktop.isDesktopSupported()) {
                            System.out.println("Desktop is not supported");
                            return
                        }
                        Desktop.getDesktop()
                            .open(it)
                    }
                }
            }
        }

        fun getScoreLineInSheet(
            workbook: Workbook,
            sheetName: String,
            gameMapList: List<GameMap>
        ) = workbook.getSheet(sheetName).let { sheet ->
            val scoreLinsList = mutableListOf<ScoreLine>()
            for (i in 0 until sheet.physicalNumberOfRows) {
                val row = sheet.getRow(i)
                ScoreLine().apply {
                    gameMap =
                        gameMapList.firstOrNull { it.name == row.getCell(0).stringCellValue }
                            ?: GameMap.defGameMap()
                    pro = row.getCell(1).stringCellValue.toFloat()
                    one = row.getCell(2).stringCellValue.toFloat()
                    two = row.getCell(3).stringCellValue.toFloat()
                    three = row.getCell(4).stringCellValue.toFloat()
                    entertainment = row.getCell(5).stringCellValue.toFloat()
                }.also {
                    scoreLinsList.add(it)
                }
            }
            scoreLinsList
        }

        fun createSheet(xssfWorkbook: XSSFWorkbook, scoreLineList: List<ScoreLine>, name: String) {
            xssfWorkbook.createSheet(name).let { sheet ->
                val hasTitle = false
                val firstRowIndex = if (hasTitle) 2 else 1
                val headerRowIndex = if (hasTitle) 1 else 0
                val mapNameIndex = 1     //地图名列
                val scoreIndex = 7      //成绩列
                val levelIndex = 8      //分数线等级列
                val factionIndex = 9      //分数列
                sheet.defaultColumnWidth = 5
                sheet.setColumnWidth(0, 4 * 256)
                sheet.setColumnWidth(mapNameIndex, 16 * 256)
                sheet.setColumnWidth(scoreIndex, 8 * 256)
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
                            val datas = arrayListOf(
                                "id",
                                "地图",
                                "主力",
                                "一线",
                                "二线",
                                "三线",
                                "娱乐",
                                "成绩",
                                "等级",
                                "分数"
                            )
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
                            val values = arrayListOf(
                                "主力", "一线", "二线", "三线", "娱乐"
                            )
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
                                value = "娱乐开外"
                            } else {
                                value =
                                    values[lineIndex] + if (score == lines[lineIndex]) "勉强" else "以上"
                            }

                            val datas = arrayListOf(
                                scoreLine.gameMap.id.toString(),
                                scoreLine.gameMap.name,
                                scoreLine.pro.toFormatString(),
                                scoreLine.one.toFormatString(),
                                scoreLine.two.toFormatString(),
                                scoreLine.three.toFormatString(),
                                scoreLine.entertainment.toFormatString(),
                                scoreLine.gameMap.getScoreResult(),
                                value,
                                faction.toString()
                            )
                            datas.forEachIndexed { index, s ->
                                val style: CellStyle
                                if (index == mapNameIndex) {
                                    style = xssfWorkbook.createCellStyle().also {
                                        it.cloneStyleFrom(cellStyle)
                                    }.also {
                                        it.alignment = HorizontalAlignment.LEFT
                                    }
                                } else if (index == scoreIndex) {
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

}