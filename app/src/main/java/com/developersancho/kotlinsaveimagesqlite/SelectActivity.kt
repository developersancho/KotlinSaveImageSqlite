package com.developersancho.kotlinsaveimagesqlite

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import com.developersancho.kotlinsaveimagesqlite.DBHelper.DBHelper
import com.developersancho.kotlinsaveimagesqlite.Utility.Utils
import kotlinx.android.synthetic.main.activity_select.*

class SelectActivity : AppCompatActivity() {

    val SELECT_PHOTO = 2222

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)

        btn_selects.setOnClickListener {
            val photoPicker = Intent(Intent(Intent.ACTION_PICK))
            photoPicker.type = "image/+"
            startActivityForResult(photoPicker, SELECT_PHOTO)
        }


        btn_save.setOnClickListener {
            // create bitmap from Image View
            val bitmap = (img_select.drawable as BitmapDrawable).bitmap
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Enter name of picture")

            val editText = EditText(this)
            alertDialog.setView(editText)

            alertDialog.setPositiveButton("OK") { dialog, which ->
                if (!TextUtils.isEmpty(editText.text.toString())) {
                    DBHelper(applicationContext).addBitmap(editText.text.toString(), Utils.getBytes(bitmap))
                    Toast.makeText(this, "Save Success!!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Please enter name of picture", Toast.LENGTH_SHORT).show()
                }
            }

            alertDialog.setNegativeButton("CANCEL") { dialog, which ->
                dialog.dismiss()
            }

            alertDialog.show()
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SELECT_PHOTO && resultCode == Activity.RESULT_OK && data != null) {
            val pickedImage = data.data
            img_select.setImageURI(pickedImage)
            btn_save.isEnabled = true
        }

    }
}
