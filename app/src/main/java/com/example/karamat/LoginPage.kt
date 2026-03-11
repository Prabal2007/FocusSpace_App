package com.example.karamat

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_page)

        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etAge = findViewById<EditText>(R.id.etAge)
        val btnContinue = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnSave)

        btnContinue.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val age = etAge.text.toString().trim()

            // Name validation
            if (name.isEmpty()) {
                etName.error = "Please enter your Name"
                etName.requestFocus()
                return@setOnClickListener
            }

            // Email validation
            if (email.isEmpty()) {
                etEmail.error = "Please enter your Email"
                etEmail.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Please enter a valid Email address"
                etEmail.requestFocus()
                return@setOnClickListener
            }

            // Phone validation
            if (phone.isEmpty()) {
                etPhone.error = "Please enter your Phone Number"
                etPhone.requestFocus()
                return@setOnClickListener
            }
            if (phone.length != 10 || !Patterns.PHONE.matcher(phone).matches()) {
                etPhone.error = "Please enter a valid Phone Number"
                etPhone.requestFocus()
                return@setOnClickListener
            }

            // Age validation
            val ageInt = age.toIntOrNull()
            if (ageInt == null || ageInt !in 1..120) {
                etAge.error = "Please enter a valid Age"
                etAge.requestFocus()
                return@setOnClickListener
            }

            // If all validations pass, navigate to MainActivity
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("NAME", name)
                putExtra("EMAIL", email)
                putExtra("PHONE", phone)
                putExtra("AGE", ageInt)
            }
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}