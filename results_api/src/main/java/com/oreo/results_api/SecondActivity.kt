package com.oreo.results_api

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val textResult = intent.getStringExtra("name")
        txt_second.text = "接收到的数据: $textResult"

        btn_jump_second.setOnClickListener {
            val intent  = Intent().apply {
                putExtra("result", "Hello, I'm Key，我是回传的数据")
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}