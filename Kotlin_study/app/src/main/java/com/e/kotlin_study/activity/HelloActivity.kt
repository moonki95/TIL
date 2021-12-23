package com.e.kotlin_study.activity

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.e.kotlin_study.R
import kotlinx.android.synthetic.main.activity_hello.*

class HelloActivity : Activity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)
        
        btnHello.setOnClickListener(){
            Toast.makeText(this, "토스트미시지",Toast.LENGTH_SHORT).show()
        }
    }
}



