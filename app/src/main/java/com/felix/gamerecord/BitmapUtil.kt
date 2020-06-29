package com.felix.gamerecord

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Align
import java.text.SimpleDateFormat
import java.util.*


fun Bitmap.drawWaybill(
    waybillNoList: List<String>,
    signName: String = "张三",
    mobile: String = "18259456123"
): Boolean {
    if (!this.isMutable) {
        return false
    }
    if (waybillNoList.isEmpty()) {
        return true
    }
    val lineSize = 2
    val mCanvas = Canvas(this)
    val w = getWidth()
    val h = getHeight()
    val paint = Paint()
    paint.setTextSize(22f)
    paint.setColor(Color.BLACK)
    mCanvas.translate(0f, 0f)//平移
    mCanvas.rotate(90f)//旋转
    paint.setColor(Color.WHITE)
    mCanvas.drawRect(0f, 0f, h.toFloat(), 42f, paint)

    paint.setColor(Color.BLACK)
    paint.setTextAlign(Align.LEFT)
    val title = "客户通过公众号扫码签收"
    val signName = "签收人 ：" + signName
    val signPhone = "电话 ：" + mobile
    //取文本最大的宽度
    val strWidth = Math.max(
        Math.max(paint.measureText(title), paint.measureText(signName)),
        paint.measureText(signPhone)
    ) / 2

    //客户通过公众号扫码签收、脱敏电话号码、签收姓名
    val y75 = 25f
    val x50 = 22f
    mCanvas.drawText(
        title,
        h / 2 - strWidth,
        -w / 2 - y75,
        paint
    )
    mCanvas.drawText(signName, h / 2 - strWidth, -w / 2.toFloat(), paint)
    mCanvas.drawText(signPhone, h / 2 - strWidth, -w / 2 + y75, paint)

    /* 画运单号 */
    paint.setColor(Color.BLUE)
    paint.setTextAlign(Align.LEFT)
    var row = waybillNoList.size / lineSize
    if (waybillNoList.size % lineSize > 0) {
        row = row + 1
    }
    for (i in 0 until row) {
        val subWaybillList: List<String>
        if (i == row - 1) {
            subWaybillList = waybillNoList.subList(i * lineSize, waybillNoList.size)
        } else {
            subWaybillList = waybillNoList.subList(i * lineSize, (i + 1) * lineSize)
        }
        val text = subWaybillList.string()
        mCanvas.drawText(
            text,
            y75,
            -(i + 1) * x50,
            paint
        )
    }
    /* 时间 */
    paint.setTextAlign(Align.RIGHT)
    mCanvas.drawText(
        getTimeString(), h - y75,
        -w + y75, paint
    )
    return true
}

fun List<String>?.string(split: String = ",") = takeIf { !it.isNullOrEmpty() }
    ?.let {
        val sb = StringBuilder()
        it.forEachIndexed { index, s ->
            if (index != 0) {
                sb.append(split)
            }
            sb.append(s)
        }
        sb.toString()
    } ?: ""

fun getTimeString(format: String = "yyyy-MM-dd HH:mm:ss"): String {
    val cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"))
    val sdf = SimpleDateFormat(format)
    return sdf.format(cal.getTime())
}

fun Bitmap.drawWaybillNo(waybillNos: List<String>): Bitmap {
    val originBp = this
    if (waybillNos.isEmpty()) {
        return originBp
    }
    val lineSize = 3
    val mCanvas = Canvas(originBp)
    val w = originBp.width
    val h = originBp.height
    val paint = Paint()
    paint.textSize = 14f
    paint.color = Color.BLACK
    mCanvas.translate(0f, 0f)//平移
    mCanvas.rotate(90f)//旋转
    paint.color = Color.WHITE
    mCanvas.drawRect(0f, 0f, h.toFloat(), 50f, paint)

    //绘制信息


    paint.color = Color.parseColor("#FFFDDC08")
    val title = "客户通过公众号扫码签收"
    val signName = "签收人 ：张三"
    val signPhone = "电话 ：18259414313"
    val strWidth = Math.max(
        Math.max(paint.measureText(title), paint.measureText(signName)),
        paint.measureText(signPhone)
    )
    val left = (h - strWidth) / 2
    val fontHeight = paint.textSize
    val space = fontHeight / 2
    mCanvas.drawText(title, left, (-w - fontHeight) / 2-space, paint)
    mCanvas.drawText(signName, left, (-w + fontHeight) / 2, paint)
    mCanvas.drawText(signPhone, left, (-w + fontHeight * 3) / 2+space, paint)


    /* 画运单号 */
    paint.color = Color.BLUE
    paint.textAlign = Align.LEFT
    var row = waybillNos.size / lineSize
    if (waybillNos.size % lineSize > 0) {
        row = row + 1
    }
    for (i in 0 until row) {
        val subWaybillList: List<String>
        if (i == row - 1) {
            subWaybillList = waybillNos.subList(i * lineSize, waybillNos.size)
        } else {
            subWaybillList = waybillNos.subList(i * lineSize, (i + 1) * lineSize)
        }
        val text = CollectionUtils.list2Str(subWaybillList)
        mCanvas.drawText(text, 14f, (-(i + 1) * 14).toFloat(), paint)
    }

    /* 时间 */
    paint.textAlign = Paint.Align.RIGHT
    mCanvas.drawText(DateUtils.getDateTime(), (h - 16).toFloat(), (-w + 16).toFloat(), paint)
    return originBp
}
