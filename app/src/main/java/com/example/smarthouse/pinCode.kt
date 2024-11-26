package com.example.smarthouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import org.w3c.dom.Text
import java.nio.BufferUnderflowException

class pinCode : AppCompatActivity() {
   private lateinit var one: EditText
    private lateinit var two: EditText
    private lateinit var three: EditText
    private lateinit var four: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin_code)
         one = findViewById(R.id.pin1)
        two = findViewById(R.id.pin2)
         three = findViewById(R.id.pin3)
         four = findViewById(R.id.pin4)

        disableKeyboard(one)
        disableKeyboard(two)
        disableKeyboard(three)
        disableKeyboard(four)

        val buttons = arrayOf(
            findViewById<Button>(R.id.key1),
            findViewById<Button>(R.id.key2),
            findViewById<Button>(R.id.key3),
            findViewById<Button>(R.id.key4),
            findViewById<Button>(R.id.key5),
            findViewById<Button>(R.id.key6),
            findViewById<Button>(R.id.key7),
            findViewById<Button>(R.id.key8),
            findViewById<Button>(R.id.key9),
        )
        one.requestFocus()

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                updateCode()
            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1) {
                    when {
                        one.hasFocus() -> two.requestFocus()
                        two.hasFocus() -> three.requestFocus()
                        three.hasFocus() -> four.requestFocus()
                    }
                }
            }

            private fun updateCode() {
                val code =
                    one.text.toString() + two.text.toString() + three.text.toString() + four.text.toString()
                if (one.length() > 0) one.setBackgroundResource(R.drawable.fill_ring)
                if (two.length() > 0) two.setBackgroundResource(R.drawable.fill_ring)
                if (three.length() > 0) three.setBackgroundResource(R.drawable.fill_ring)
                if (four.length() > 0) four.setBackgroundResource(R.drawable.fill_ring)


            }
        }

        one.addTextChangedListener(textWatcher)
        two.addTextChangedListener(textWatcher)
        three.addTextChangedListener(textWatcher)
        four.addTextChangedListener(textWatcher)

        for (button in buttons) {
            button.setOnClickListener { onNumberButtonClick(button.text.toString()) }
        }
    }
   private fun onNumberButtonClick(number: String) {
        when {
            one.text.isEmpty() -> one.setText(number)
            two.text.isEmpty() -> two.setText(number)
            three.text.isEmpty() -> three.setText(number)
            four.text.isEmpty() -> {
                four.setText(number)
                val code =
                    one.text.toString() + two.text.toString() + three.text.toString() + four.text.toString()
           val sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE)
                sharedPreferences.edit().putString("savedPinCode", code).apply()
                val intent = Intent(this, MainMenu::class.java)
                startActivity(intent)
            }
            }
   }
    private fun disableKeyboard(editText: EditText) {
        editText.showSoftInputOnFocus = false
    }
}