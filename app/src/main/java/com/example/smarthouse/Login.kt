package com.example.smarthouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val regb :Button = findViewById(R.id.regb)
        val nextb : Button = findViewById(R.id.nextb)
        regb.setOnClickListener {
            val Intent : Intent = Intent(this, registration::class.java)
            startActivity(Intent)
        }
       nextb.setOnClickListener {
           val Intent2 :Intent = Intent(this@Login, adressenter::class.java)
           startActivity(Intent2)
       }
    }
}