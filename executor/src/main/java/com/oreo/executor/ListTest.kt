package com.oreo.executor

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.act_list_test.*
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class ListTest :AppCompatActivity(){

    val data = arrayListOf("123","123","123","123","123","123","123","123","123","123","123","123"
        ,"123","123","123","123","123","123","123","123","123","123","123","123","123","123","123"
        ,"456","456","456","456","456","456","456","456","456","456","456")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_list_test)
        list_test_1.adapter = object :BaseRecyclerAdapter<String>(this,data
            ,R.layout.list_item,null) {
            override fun bindHolder(holder: BaseViewHolder?, item: String?, position: Int) {
                holder?.getView<TextView>(R.id.tv_item)?.text = item
            }
        }

        (list_test_1.adapter as BaseRecyclerAdapter<String>).notifyDataSetChanged()
    }
}

/**
 * 获取recyclerview截图
 */
fun getRecyclerviewBitmap(recyclerview: RecyclerView, picpath: String?): Bitmap? {
    var h = 0
    val bitmap: Bitmap
    // 获取recyclerview实际高度
    for (i in 0 until recyclerview.childCount) {
        h += recyclerview.getChildAt(i).height
    }
    // 创建对应大小的bitmap
    bitmap = Bitmap.createBitmap(
        recyclerview.width, h,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    recyclerview.draw(canvas)
    // 测试输出
    var out: FileOutputStream? = null
    try {
        out = FileOutputStream(picpath)
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    }
    try {
        if (null != out) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
            out.flush()
            out.close()
        }
    } catch (e: IOException) {
    }
    return bitmap
}