package com.example.smarthouse

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts

class Profile : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val image : ImageView = findViewById(R.id.profilephotoset)
        val SelectImageIntent  = registerForActivityResult(ActivityResultContracts.GetContent()) {
            uri -> image.setImageURI(uri)
        }
        image.setOnClickListener {
            SelectImageIntent.launch("image/*")
        }
    }
}