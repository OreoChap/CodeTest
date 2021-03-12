package com.oreo.flow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.act_flow.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class FlowAct : AppCompatActivity() {

    lateinit var flow: Flow<Int>
    lateinit var flowOne:Flow<String>
    lateinit var flowTwo:Flow<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_flow)
        setupFlow()
        setupClicks()
    }

    private fun setupFlow() {
        flow = flow {
            Log.d(TAG, "Start flow")
            (0..10).forEach {
                delay(500)
                Log.d(TAG, "Emitting $it")
                emit(it)
            }
        }.map {
            it * it
        }.flowOn(Dispatchers.Default)

//        flowOne = flowOf("Himanshu", "Amit", "Janshit").flowOn(Dispatchers.Default)
//        flowTwo = flowOf("Singh", "Shekhar", "Ali").flowOn(Dispatchers.Default)
    }

    fun setupClicks() {
        button.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {
                flow.collect {
                    Log.d(TAG, it.toString())
                }

//                flowOne.zip(flowTwo){
//                    firstString, secondString ->
//                    "$firstString $secondString"
//                }.collect{
//                    Log.d(TAG, it)
//                }
            }
        }
    }

    fun main() = runBlocking {

    }

    companion object {
        val TAG = "xyz"
    }
}