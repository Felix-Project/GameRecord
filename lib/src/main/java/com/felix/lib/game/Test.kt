package com.felix.lib.game

import com.felix.lib.bean.GameMap
import com.felix.lib.bean.ScoreLine
import com.google.gson.reflect.TypeToken
import okio.buffer
import okio.sink
import okio.source
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.util.regex.Pattern

class Test {
    companion object {
        val root = File("game")
        @JvmStatic
        fun main(args: Array<String>) {
            if (!root.exists()) {
                root.mkdir()
            }
            readMap()
        }

        fun readMap() {
            val mapList = mutableListOf<GameMap>()
            var index = 0
            val pattern = Pattern.compile("[12].[0-9]{2}(.[0-9]{2})?")

            File("car.xlsx").inputStream()
                .let { XSSFWorkbook(it) }
                .also {
                    //GameMap
                    it.getSheetAt(4)
                        .let { sheet ->
                            for (i in 0 until sheet.physicalNumberOfRows) {
                                sheet.getRow(i)?.let {
                                    it.getCell(7)?.takeIf { it.cellType == CellType.STRING }
                                        ?.let { it.stringCellValue }
                                        ?.takeIf { pattern.matcher(it).matches() }
                                        ?.let { value ->
                                            val gameMap = GameMap()
                                            gameMap.score = value.replaceFirst(".", "").toFloat()
                                            gameMap.name = it.getCell(1).stringCellValue.trim()
                                            gameMap.id = ++index
                                            mapList.add(gameMap)
                                        }
                                }
                            }
                        }
                    mapList.toJson().let {
                        File(root, "GameMap.txt").outputStream().sink().buffer().let { buffer ->
                            buffer.writeUtf8(it)
                            buffer.flush()
                        }
                        println(it)
                    }
                }
                .also {
                    getSheetResult(
                        it,
                        1,
                        "s1.txt",
                        0
                    )
                }
                .also {
                    getSheetResult(
                        it,
                        2,
                        "s3.txt",
                        2
                    )
                }
                .also {
                    getSheetResult(
                        it,
                        3,
                        "s4.txt",
                        1
                    )
                }
                .also {
                    getSheetResult(
                        it,
                        4,
                        "s5.txt",
                        1,
                        1
                    )
                }


        }

        fun getSheetResult(
            workbook: Workbook,
            sheetIndex: Int,
            outName: String,
            firstRow: Int,
            firstCell: Int = 0
        ) {
            var mapList = File(root, "GameMap.txt").inputStream().source().buffer()
                .readString(Charsets.UTF_8)
                .fromJson<List<GameMap>>(object : TypeToken<List<GameMap>>() {}.type)

            var scoreLineList = mutableListOf<ScoreLine>()
            workbook.getSheetAt(sheetIndex)
                .let { sheet ->
                    for (i in firstRow until sheet.physicalNumberOfRows) {
                        val row = sheet.getRow(i)
                        val index = firstCell
                        var first =
                            mapList.firstOrNull { row.getCell(index + 0).getResult() == it.name }
                                ?: GameMap().apply {
                                    name = "unknow"
                                }
                        val scoreLine = ScoreLine()
                        scoreLine.gameMap = first
                        scoreLine.pro = row.getCell(index + 1).getResult().toFloat()
                        scoreLine.one = row.getCell(index + 2).getResult().toFloat()
                        scoreLine.two = row.getCell(index + 3).getResult().toFloat()
                        scoreLine.three = row.getCell(index + 4).getResult().toFloat()
                        scoreLine.entertainment =
                            row.getCell(index + 5).getResult().replace("+", "").toFloat()
                        scoreLineList.add(scoreLine)
                    }
                }
            scoreLineList
                .also { println(it.size) }
                .toJson().let {
                    File(root, outName).outputStream().sink().buffer().let { buffer ->
                        buffer.writeUtf8(it)
                        buffer.flush()
                    }
                    println(it)
                }
        }

    }
}


fun Cell.getResult(): String {
    return when (cellType) {
        CellType.STRING -> stringCellValue
        CellType.NUMERIC -> numericCellValue.toString()
        else -> toString()
    }?.replace(" ", "")?.replace(",", ".") ?: "null"
}