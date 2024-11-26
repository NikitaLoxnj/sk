package com.example.smarthouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.ImageView
import java.util.logging.Handler

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val splashView : ImageView = findViewById(R.id.splashview)
        val sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        val savedPinCode = sharedPreferences.getString("savedPinCode", null)
        splashView.animate().rotation(360f).setDuration(3000).start()

        val nextActivity = if(savedPinCode !=null){
            pinCodeready::class.java
        } else {
            Login::class.java
        }

        android.os.Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, nextActivity)
       startActivity(intent)
            finish()
        },5000)

    }
}