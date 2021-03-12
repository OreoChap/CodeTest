package com.oreo.thread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val t1 = object :Thread() {
            override fun run() {
                super.run()
                runOnUiThread {
                    Log.e("xyz" ,"UIThread:" + Thread.currentThread())

                }
                Log.e("xyz" ,"" + Thread.currentThread())
                Looper.prepare()
                val handler = object :Handler() {
                    override fun handleMessage(msg: Message) {
                        super.handleMessage(msg)
                        Log.e("xyz","???"+ Thread.currentThread())
                    }
                }

                handler.sendEmptyMessage(1)
                Looper.loop()
            }

        }.start()
    }
}


// threadLocal 的使用
class Demo {
    companion object {
        var threadLocal: ThreadLocal<Int>? = null

        @JvmStatic
        fun main(args: Array<String>) {
            threadLocal = object : ThreadLocal<Int>() {
                override fun initialValue(): Int? {
                    return 10
                }
            }


            val t1 = MyThread(20)
            val t2 = MyThread(30)

            t1.start()

            try {
                t1.join()
            }catch (e:Exception) {
            }

            t2.start()
        }
    }
}

class MyThread(abc: Int) : Thread() {

    private val value: Int = abc

    override fun run() {
        println(currentThread().toString() + "-BEFORE-" + Demo.threadLocal?.get());
        Demo.threadLocal?.set(value)
        println(currentThread().toString() + "-AFTER-" + Demo.threadLocal?.get());
    }
}