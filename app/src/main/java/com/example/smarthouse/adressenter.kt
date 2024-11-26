package com.example.smarthouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class adressenter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adressenter)
        val address : EditText = findViewById(R.id.addressenter)
        val save : Button = findViewById(R.id.saveaddress)

        val supabase = createSupabaseClient(
            supabaseUrl = "https://xsbncskzfmtkbdvereuu.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InhzYm5jc2t6Zm10a2JkdmVyZXV1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzEwNTgwOTIsImV4cCI6MjA0NjYzNDA5Mn0.1z-Fe4aZYhrIIbiOwC5Wd3etlSiVBqLDf7klCvsw_xM"
        ){
            install(Postgrest)
        }
        save.setOnClickListener {
            MainScope().launch {
                val a = address.text.toString().trim()
                val homes = Homes(id=1, address = a)
                supabase.from("homes").insert(homes)

            }
            val intent = Intent(this@adressenter, pinCode::class.java)
            startActivity(intent)
        }
    }
}