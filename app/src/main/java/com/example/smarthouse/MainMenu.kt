package com.example.smarthouse

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
val menu :RecyclerView = findViewById(R.id.menu)
        val but : Button = findViewById(R.id.addroomb)
        val setad :TextView = findViewById(R.id.setaddress)
        val rec : RecyclerView = findViewById(R.id.rec)
        menu.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
val men : ArrayList<String> = ArrayList()
       men.add("Комнаты")
        men.add("Устройства")
        men.add("Пользователи")
menu.adapter = menu_adapter(men)
        but.setOnClickListener {
            val intent = Intent(this, addroom::class.java)
        startActivity(intent)
        }

        val supabase = createSupabaseClient(
            supabaseUrl = "https://xsbncskzfmtkbdvereuu.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InhzYm5jc2t6Zm10a2JkdmVyZXV1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzEwNTgwOTIsImV4cCI6MjA0NjYzNDA5Mn0.1z-Fe4aZYhrIIbiOwC5Wd3etlSiVBqLDf7klCvsw_xM"
        ){
            install(Postgrest)
        }
        MainScope().launch {
            val response = supabase.from("homes").select().decodeSingle<Homes>()
            setad.text = response.address
        val getRoom = supabase.from("rooms").select().decodeList<Rooms>()
        rec.layoutManager = LinearLayoutManager(this@MainMenu, LinearLayoutManager.VERTICAL, false)
            rec.adapter = RoomAdapter(getRoom ?: emptyList())
        }

    }

}