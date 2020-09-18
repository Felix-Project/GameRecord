package com.felix.lib.game

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException


class ReadExcelUtils(filepath: String) {
    private var wb: Workbook? = null
    private var sheet: Sheet? = null
    private var row: Row? = null

    init {
        val ext = filepath.substring(filepath.lastIndexOf("."))
        try {
            val `is` = FileInputStream(filepath)
            if (".xls" == ext) {
                wb = HSSFWorkbook(`is`)
            } else if (".xlsx" == ext) {
                wb = XSSFWorkbook(`is`)
            } else {
                wb = null
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    /**
     * 读取Excel表格表头的内容
     *
     * @param InputStream
     * @return String 表头内容的数组
     * @author zengwendong
     */
    @Throws(Exception::class)
    fun readExcelTitle(): Array<String?> {
        if (wb == null) {
            throw Exception("Workbook对象为空！")
        }
        sheet = wb!!.getSheetAt(0)
        row = sheet!!.getRow(0)
        // 标题总列数
        val colNum = row!!.getPhysicalNumberOfCells()
        println("colNum:$colNum")
        val title = arrayOfNulls<String>(colNum)
        for (i in 0 until colNum) {
            // title[i] = getStringCellValue(row.getCell((short) i));
            title[i] = row!!.getCell(i).getCellFormula()
        }
        return title
    }

    /**
     * 读取Excel数据内容
     *
     * @param InputStream
     * @return Map 包含单元格数据内容的Map对象
     * @author zengwendong
     */
    @Throws(Exception::class)
    fun readExcelContent(): Map<Int, Map<Int, Any>> {
        if (wb == null) {
            throw Exception("Workbook对象为空！")
        }
        val content = HashMap<Int, Map<Int, Any>>()

        sheet = wb!!.getSheetAt(0)
        // 得到总行数
        val rowNum = sheet!!.getLastRowNum()
        row = sheet!!.getRow(0)
        val colNum = row!!.getPhysicalNumberOfCells()
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (i in 1..rowNum) {
            row = sheet!!.getRow(i)
            var j = 0
            val cellValue = HashMap<Int, Any>()
            while (j < colNum) {
                val obj = getCellFormatValue(row!!.getCell(j))
                cellValue.put(j, obj)
                j++
            }
            content.put(i, cellValue)
        }
        return content
    }

    /**
     *
     * 根据Cell类型设置数据
     *
     * @param cell
     * @return
     * @author zengwendong
     */
    private fun getCellFormatValue(cell: Cell?): Any {
        var cellvalue: Any = ""
        if (cell != null) {
            // 判断当前Cell的Type
            when (cell.getCellType()) {
                CellType.NUMERIC// 如果当前Cell的Type为NUMERIC
                    , CellType.FORMULA -> {
                    // 判断当前的cell是否为Date
                    if (DateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式
                        // data格式是带时分秒的：2013-7-10 0:00:00
                        // cellvalue = cell.getDateCellValue().toLocaleString();
                        // data格式是不带带时分秒的：2013-7-10
                        val date = cell.getDateCellValue()
                        cellvalue = date
                    } else {// 如果是纯数字

                        // 取得当前Cell的数值
                        cellvalue = cell.getNumericCellValue().toString()
                    }
                }
                CellType.STRING// 如果当前Cell的Type为STRING
                ->
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString()
                else// 默认的Cell值
                -> cellvalue = ""
            }
        } else {
            cellvalue = ""
        }
        return cellvalue
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {

            val file = File("car.xlsx")
//            file.inputStream().let {
//                HSSFWorkbook(it)
//            }.let { wb ->
//                wb.sheetIterator().let {
//                    while (it.hasNext()) {
//                        val sheet = it.next()
//
//                    }
//                }
//            }
            try {
                val filepath = file.absolutePath
                val excelReader = ReadExcelUtils(filepath)
                // 对读取Excel表格标题测试
                //			String[] title = excelReader.readExcelTitle();
                //			System.out.println("获得Excel表格的标题:");
                //			for (String s : title) {
                //				System.out.print(s + " ");
                //			}

                // 对读取Excel表格内容测试
                val map = excelReader.readExcelContent()
                println("获得Excel表格的内容:")
                for (i in 1..map.size) {
                    println(map[i])
                }
            } catch (e: FileNotFoundException) {
                println("未找到指定路径的文件!")
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}
