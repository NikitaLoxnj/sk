package com.example.smarthouse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class addroom : AppCompatActivity() {
    private val supabase = createSupabaseClient(
        supabaseUrl = "https://xsbncskzfmtkbdvereuu.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InhzYm5jc2t6Zm10a2JkdmVyZXV1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzEwNTgwOTIsImV4cCI6MjA0NjYzNDA5Mn0.1z-Fe4aZYhrIIbiOwC5Wd3etlSiVBqLDf7klCvsw_xM"
    ) {
        install(Postgrest)
    }

    private var selectedRoomType: RoomType? = null // Выбранный тип комнаты

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addroom)

        val roomTypesRecyclerView: RecyclerView = findViewById(R.id.roomTypeadd)
        val addRoomButton: Button = findViewById(R.id.addRoomb)
        val backButton: Button = findViewById(R.id.backadd)
        val roomNameEditText: EditText = findViewById(R.id.roomn)

        backButton.setOnClickListener {
            finish()
        }

        // Загрузка данных из базы данных Supabase
        MainScope().launch {
            try {
                val response = supabase.from("types_room").select().decodeList<RoomType>()

                // Установка адаптера с обратным вызовом
                roomTypesRecyclerView.adapter =
                    RoomTypesAdapter(response ?: emptyList()) { selectedTypes ->
                        // Устанавливаем выбранный тип комнаты (единственный выбранный)
                        selectedRoomType = selectedTypes.firstOrNull()

                        // Тост для отображения текущего выбранного типа
                        selectedRoomType?.let {
                            Toast.makeText(
                                this@addroom,
                                "Выбрано: ${it.typename}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                roomTypesRecyclerView.layoutManager = GridLayoutManager(this@addroom, 3)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@addroom, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show()
            }
        }

        // Обработка нажатия на кнопку добавления комнаты
        addRoomButton.setOnClickListener {
            val roomName = roomNameEditText.text.toString().trim()
            if (selectedRoomType != null && roomName.isNotBlank()) {
                MainScope().launch {
                    try {
                        val Room = Rooms(id = 2, home_id = 1, name = roomName, types = selectedRoomType!!.id)
                        val result = supabase.from("rooms").insert(Room)
                        result?.let {
                            Toast.makeText(this@addroom, "Комната добавлена!", Toast.LENGTH_SHORT)
                                .show()
                            finish()
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(
                            this@addroom,
                            "Ошибка при добавлении комнаты",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(
                    this,
                    "Выберите тип комнаты и введите название",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

