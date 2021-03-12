package com.oreo.executor

import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.Executors

class SingleThreadWebServer {
    companion object {
        val THREAD_COUNT = 100
        val exec = Executors.newFixedThreadPool(THREAD_COUNT)

        @JvmStatic
        fun main(args: Array<String>) {
            val server = ServerSocket(7711)
            while (true) {
                val socket = server.accept()


                exec.execute {
                    handleReq(socket)
                }
            }
        }

        fun handleReq(client: Socket) {

        }
    }
}