package com.oreo.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity

private const val tag = "123"

fun d(tag:String = com.oreo.service.tag,text:String) {

}

class ServiceAct : AppCompatActivity() {

    private val mConnect: ServiceConnection = TestServiceConnection()
    var mService: TestService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        bindService(Intent(this, TestService::class.java), mConnect, Context.BIND_AUTO_CREATE)
    }


    inner class TestServiceConnection : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mService = (service as TestService.TestBinder).getService()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mService = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(mConnect)
    }

    companion object{
    }



}
