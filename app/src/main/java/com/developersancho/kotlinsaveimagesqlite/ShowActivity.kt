package com.developersancho.kotlinsaveimagesqlite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.developersancho.kotlinsaveimagesqlite.DBHelper.DBHelper
import com.developersancho.kotlinsaveimagesqlite.Utility.Utils
import kotlinx.android.synthetic.main.activity_show.*

class ShowActivity : AppCompatActivity() {

    internal lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        dbHelper = DBHelper(this)

        btn_shows.setOnClickListener {
            if (dbHelper.getBitmapByName(edt_name.text.toString()) != null) {
                val bitmap = Utils.getImage(dbHelper.getBitmapByName(edt_name.text.toString())!!)
                img_show.setImageBitmap(bitmap)
            } else {
                Toast.makeText(this, "Can not found bitmap", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
