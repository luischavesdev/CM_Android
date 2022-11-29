package com.example.todolist

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.quick_numb_screen.*


class DialButton : AppCompatActivity() {

    private var  receivedVal = -1
    private var quickButtonsNames = arrayOf("", "", "")
    private var quickButtonsNums = arrayOf("", "", "")

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.quick_numb_screen)

        // Check incoming button value
        var receivedBundle = intent.extras
        if (receivedBundle!= null){
            receivedVal = receivedBundle.getInt("btnValue")
            quickButtonsNames = receivedBundle.getStringArray("btnNames")!!
            quickButtonsNums = receivedBundle.getStringArray("btnNums")!!
        }

        // Apply Quick Button
        btSetQuickContact.setOnClickListener(){

            if (receivedVal > -1 && etContactName.text.isNotEmpty() && etContactName.text.length < 8 && etContactNumber.text.isNotEmpty()) {

                quickButtonsNames[receivedVal] = etContactName.text.toString()
                quickButtonsNums[receivedVal] = etContactNumber.text.toString()

                val myIntent = Intent(this, MainActivity::class.java)
                myIntent.putExtra("btnValue", receivedVal)
                myIntent.putExtra("btnNames", quickButtonsNames)
                myIntent.putExtra("btnNumbs", quickButtonsNums)
                startActivity(myIntent)
            }
        }
    }
}