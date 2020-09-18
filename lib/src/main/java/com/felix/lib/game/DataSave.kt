package com.felix.lib.game

import com.felix.lib.bean.GameMap
import com.felix.lib.bean.ScoreLine
import com.felix.lib.bean.toFormatString
import com.google.gson.reflect.TypeToken
import okio.buffer
import okio.source
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.awt.Desktop
import java.io.File

class DataSave {
    companion object {
        val root = File("game")
        @JvmStatic
        fun main(args: Array<String>) {
            XSSFWorkbook().also {
                val gameMapList =
                    File(root, "GameMap.txt").inputStream().source().buffer()
                        .readString(Charsets.UTF_8)
                        .fromJson<List<GameMap>>(object : TypeToken<List<GameMap>>() {}.type)
                it.createSheet("gameMap").let { sheet ->
                    sheet.defaultColumnWidth = 16
                    gameMapList.forEachIndexed { rowIndex, scoreLine ->
                        sheet.createRow(rowIndex).apply {
                            val values = arrayOf(
                                scoreLine.name,
                                scoreLine.score.toFormatString()
                            )
                            values.forEachIndexed { cellIndex, s ->
                                if (s is String) {
                                    createCell(cellIndex).setCellValue(s)
                                }
                            }
                        }
                    }
                }
            }.also {
                createSheet(it, "s1.txt")
                createSheet(it, "s3.txt")
                createSheet(it, "s4.txt")
                createSheet(it, "s5.txt")
            }.also { workBook ->
                File(root, "data.xlsx").also {
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

        fun createSheet(workbook: Workbook, path: String) {
            val name = path.substring(0, path.indexOf("."))
            val scoreLineList =
                File(root, path).inputStream().source().buffer().readString(Charsets.UTF_8)
                    .fromJson<List<ScoreLine>>(object : TypeToken<List<ScoreLine>>() {}.type)
            workbook.createSheet(name).let { sheet ->
                scoreLineList.forEachIndexed { rowIndex, scoreLine ->
                    sheet.createRow(rowIndex).apply {
                        val values = arrayOf(
                            scoreLine.gameMap.name,
                            scoreLine.pro.toFormatString(),
                            scoreLine.one.toFormatString(),
                            scoreLine.two.toFormatString(),
                            scoreLine.three.toFormatString(),
                            scoreLine.entertainment.toFormatString()
                        )
                        values.forEachIndexed { cellIndex, s ->
                            createCell(cellIndex).setCellValue(s)
                        }
                    }
                }
            }

        }
    }
}