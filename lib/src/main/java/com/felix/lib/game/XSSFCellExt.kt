package com.felix.lib.game

import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.xssf.usermodel.XSSFCell

fun XSSFCell.style(cellStyle: CellStyle): XSSFCell {
    setCellStyle(cellStyle)
    return this
}