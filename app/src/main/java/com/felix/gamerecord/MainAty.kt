package com.felix.gamerecord

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.liulishuo.filedownloader.FileDownloader
import kotlinx.android.synthetic.main.main_aty.*
import java.lang.ref.ReferenceQueue
import java.lang.ref.WeakReference
import java.util.concurrent.Executors

class MainAty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_aty)
        test()
//        Bitmap.createBitmap(640, 480, Bitmap.Config.RGB_565, true)
//            .also {
//                it.drawWaybillNo(arrayListOf("SF1234567891011", "SF11111111111", "SF22222222222"))
//            }
//            .let {
//                Log.d("hmf", "setBitmap")
//                ivImg.setImageBitmap(it)
//            }
    }
    fun test() {
        val referenceQueue = ReferenceQueue<Bitmap>()
        var createBitmap = Bitmap.createBitmap(2000, 2000, Bitmap.Config.ARGB_8888)
        val softReferencc = WeakReference<Bitmap>(createBitmap, referenceQueue)
        softReferencc.let {
            println(it.get())
        }
        System.gc()

        softReferencc.let {
            println("====${it.get()}====")
        }

        Executors.newCachedThreadPool().execute {
            while (true){
                referenceQueue.poll()?.let {
                    println(it)
                }
            }
        }
        Thread.sleep(20000)
    }
    fun test2() {
        FileDownloader.getImpl().also {
            it.setMaxNetworkThreadCount(4)
        }.create("").start()
    }
}