package com.example.todolist

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialer_top_row.*
import kotlinx.android.synthetic.main.dialer_top_row.view.*

class MainActivity : AppCompatActivity() {

    private var  receivedVal = -1
    private var quickButtonsNames = arrayOf("-", "-", "-")
    private var quickButtonsNums = arrayOf("", "", "")

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quickButtons = arrayOf(btnQuick1, btnQuick2, btnQuick3)

        // Check incoming quick button value if any
        var receivedBundle = intent.extras
        if (receivedBundle!= null){
            receivedVal = receivedBundle.getInt("btnValue")
            quickButtonsNames = receivedBundle.getStringArray("btnNames")!!
            quickButtonsNums = receivedBundle.getStringArray("btnNumbs")!!

            for ((i, button) in quickButtons.withIndex()) {
                button.text = quickButtonsNames[i]
            }
        }

        // Check permission for interfacing with phone call API
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE), 101)
        }

        // Btns setup
        btnDial1.setOnClickListener(){
            addToDialText(btnDial1.text.toString())
        }
        btnDial2.setOnClickListener(){
            addToDialText(btnDial2.text.toString())
        }
        btnDial3.setOnClickListener(){
            addToDialText(btnDial3.text.toString())
        }
        btnDial3.setOnClickListener(){
            addToDialText(btnDial3.text.toString())
        }
        btnDial4.setOnClickListener(){
            addToDialText(btnDial4.text.toString())
        }
        btnDial5.setOnClickListener(){
            addToDialText(btnDial5.text.toString())
        }
        btnDial6.setOnClickListener(){
            addToDialText(btnDial6.text.toString())
        }
        btnDial7.setOnClickListener(){
            addToDialText(btnDial7.text.toString())
        }
        btnDial8.setOnClickListener(){
            addToDialText(btnDial8.text.toString())
        }
        btnDial9.setOnClickListener(){
            addToDialText(btnDial9.text.toString())
        }
        btnDial0.setOnClickListener(){
            addToDialText(btnDial0.text.toString())
        }
        btnDialAst.setOnClickListener(){
            addToDialText(btnDialAst.text.toString())
        }
        btnDialCard.setOnClickListener {
            addToDialText(btnDialCard.text.toString())
        }
        topRow.btnErase.setOnClickListener(){
            if (topRow.txtDial.text.isNotEmpty()){
                topRow.txtDial.text = topRow.txtDial.text.substring(0, topRow.txtDial.text.length - 1)
            }
        }
        btnPhone.setOnClickListener(){
            val tempText : String = topRow.txtDial.text.toString()
            val myCallIntent = Intent(Intent.ACTION_CALL)
            myCallIntent.data = Uri.parse("tel:$tempText")
            startActivity(myCallIntent)
        }

        // Quick btns setup
        btnQuick1.setOnClickListener(){
            quickCall(0)
        }
        btnQuick1.setOnLongClickListener(){
            startQuickBtnActivity(0)
            true
        }
        btnQuick2.setOnClickListener(){
            quickCall(1)
        }
        btnQuick2.setOnLongClickListener(){
            startQuickBtnActivity(1)
            true
        }
        btnQuick3.setOnClickListener(){
            quickCall(2)
        }
        btnQuick3.setOnLongClickListener(){
            startQuickBtnActivity(2)
            true
        }
    }

    private fun addToDialText(newNumber : String) {

        if (topRow.txtDial.text.length < 16){
            topRow.txtDial.text = "${topRow.txtDial.text}$newNumber"
        }
    }

    private fun startQuickBtnActivity(btnVal : Int) {

        val myIntent = Intent(this, DialButton::class.java)
        myIntent.putExtra("btnValue", btnVal)
        myIntent.putExtra("btnNames", quickButtonsNames)
        myIntent.putExtra("btnNums", quickButtonsNums)
        startActivity(myIntent)
    }

    private fun quickCall(btnVal : Int) {

        if (quickButtonsNums[btnVal].isNotEmpty()){
            val tempText : String = quickButtonsNums[btnVal]
            val myCallIntent = Intent(Intent.ACTION_CALL)
            myCallIntent.data = Uri.parse("tel:$tempText")
            startActivity(myCallIntent)
        }
    }
}