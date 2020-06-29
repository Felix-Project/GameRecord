package com.felix.gamerecord

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.main_aty.*

class MainAty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_aty)
        Bitmap.createBitmap(640, 480, Bitmap.Config.RGB_565, true)
            .also {
                it.drawWaybillNo(arrayListOf("SF1234567891011", "SF11111111111", "SF22222222222"))
            }
            .let {
                Log.d("hmf", "setBitmap")
                ivImg.setImageBitmap(it)
            }
    }
}