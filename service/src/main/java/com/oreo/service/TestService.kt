package com.oreo.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

private const val tag = "xyz"

class TestService : Service() {

    private val iBinder = TestBinder()
    private val executorService: ScheduledExecutorService = Executors.newScheduledThreadPool(1)
    private  var timerTask: TimerTask? = null

    override fun onCreate() {
        super.onCreate()

        if (timerTask ==null) {
            timerTask = object : TimerTask() {
                override fun run() {
                    count++
                    Log.e(tag, "count= " + getCount())
                }
            }
            executorService.scheduleAtFixedRate(timerTask,0,1, TimeUnit.SECONDS)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        threadDisable = true
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.e(tag, "onBind")
        return iBinder
    }

    companion object {
        var count: Int = 0
//        var threadDisable = true

    }

    inner class TestBinder : Binder() {
        fun getService(): TestService {
            return this@TestService
        }
    }

    // service 与 activity 通信
    fun getCount():Int {
       return count
    }
}