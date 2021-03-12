package com.oreo.results_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import kotlinx.android.synthetic.main.activity_results.*

// AppCompatActivity 没办法使用 registerForActivityResult() ??

class FirstActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        btn_jump_result.setOnClickListener {
            myActivityLauncher.launch("Hello Oreo~")
        }
    }

    private val myActivityLauncher:ActivityResultLauncher<String> = registerForActivityResult(MyActivityResultContract()){ result ->
        Toast.makeText(applicationContext,result, Toast.LENGTH_SHORT).show()
        txt_results.text = "回传数据：$result"
    }
}
