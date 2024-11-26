package com.example.smarthouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.OtpType.Email
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class registration : AppCompatActivity() {
    private lateinit var enter: Button
    private lateinit var usernamereg: EditText
    private lateinit var mailreg: EditText
    private lateinit var passreg: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        enter = findViewById(R.id.enter)
        usernamereg = findViewById(R.id.usernamereg)
        mailreg = findViewById(R.id.mailreg)
        passreg = findViewById(R.id.passreg)
        val supabase = createSupabaseClient(supabaseUrl = "https://xsbncskzfmtkbdvereuu.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InhzYm5jc2t6Zm10a2JkdmVyZXV1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzEwNTgwOTIsImV4cCI6MjA0NjYzNDA5Mn0.1z-Fe4aZYhrIIbiOwC5Wd3etlSiVBqLDf7klCvsw_xM"
        ) {
            install(Postgrest)
        }
    }
}