package com.oreo.thread

import android.os.AsyncTask

//后面尖括号内分别是参数（例子里是线程休息时间），进度(publishProgress用到)，返回值类型
class Download:AsyncTask<Int,Int,String>() {

    //第一个执行方法
    override fun onPreExecute() {
        super.onPreExecute()
    }

    //第二个执行方法,onPreExecute()执行完后执行
    override fun doInBackground(vararg params: Int?): String {
        for (i in 0..100) {
            publishProgress(i)
            try {
                Thread.sleep(params[0] as Long)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        return "执行完毕"
    }

    //这个函数在doInBackground调用publishProgress时触发，虽然调用时只有一个参数
    //但是这里取到的是一个数组,所以要用progesss[0]来取值
    //第n个参数就用progress[n]来取值
    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
    }

    //doInBackground返回时触发，换句话说，就是doInBackground执行完后触发
    //这里的result就是上面doInBackground执行后的返回值，所以这里是"执行完毕"
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
    }

    //1. 处理单个异步任务简单，可以获取到异步任务的进度
    //2. 可以通过cancel方法取消还没执行完的AsyncTask
    //3. 处理多个异步任务代码显得较多
}