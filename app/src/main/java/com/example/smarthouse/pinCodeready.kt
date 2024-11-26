package com.example.smarthouse

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class pinCodeready : AppCompatActivity() {
    private lateinit var one: EditText
    private lateinit var two: EditText
    private lateinit var three: EditText
    private lateinit var four: EditText
    private lateinit var exitacc :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin_codeready)
        one = findViewById(R.id.p1)
        two = findViewById(R.id.p2)
        three = findViewById(R.id.p3)
        four = findViewById(R.id.p4)
        exitacc = findViewById(R.id.exitacc)

        disableKeyboard(one)
        disableKeyboard(two)
        disableKeyboard(three)
        disableKeyboard(four)

        val buttons = arrayOf(
            findViewById<Button>(R.id.k1),
            findViewById<Button>(R.id.k2),
            findViewById<Button>(R.id.k3),
            findViewById<Button>(R.id.k4),
            findViewById<Button>(R.id.k5),
            findViewById<Button>(R.id.k6),
            findViewById<Button>(R.id.k7),
            findViewById<Button>(R.id.k8),
            findViewById<Button>(R.id.k9),
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
        exitacc.setOnClickListener {
            clearPinCode()
            navigateToLoginScreen()
        }

    }
    private fun onNumberButtonClick(number: String) {
        when {
            one.text.isEmpty() -> one.setText(number)
            two.text.isEmpty() -> two.setText(number)
            three.text.isEmpty() -> three.setText(number)
            four.text.isEmpty() -> {
                four.setText(number)
                val enteredCode =
                    one.text.toString() + two.text.toString() + three.text.toString() + four.text.toString()
                val sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE)
                val savedPinCode = sharedPreferences.getString("savedPinCode", "")

                if (enteredCode == savedPinCode) {
                    val intent = Intent(this, MainMenu::class.java)
                    startActivity(intent)
                } else {
                    // Очистка полей при неверном вводе
                    one.text.clear()
                    one.setBackgroundResource(R.drawable.nofill_ring)
                    two.text.clear()
                    two.setBackgroundResource(R.drawable.nofill_ring)
                    three.text.clear()
                    three.setBackgroundResource(R.drawable.nofill_ring)
                    four.text.clear()
                    four.setBackgroundResource(R.drawable.nofill_ring)
                    Toast.makeText(this, "Неверный код. Попробуйте снова.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun disableKeyboard(editText: EditText) {
        editText.showSoftInputOnFocus = false
    }
    private fun clearPinCode() {
        val sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("savedPinCode") // Удаляем сохранённый PIN-код
        editor.apply()
    }

    // Метод для перехода на экран входа
    private fun navigateToLoginScreen() {
        val intent = Intent(this, Login::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish() // Закрываем текущее Activity
    }
}