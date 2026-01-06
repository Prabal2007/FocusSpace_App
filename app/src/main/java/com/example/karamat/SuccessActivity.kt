package com.example.karamat

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SuccessActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            // Goes back to the Welcome Screen
            val intent = Intent(this, MainActivity::class.java)
            // This flag clears the history so the user can't "Back" into the success screen
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        val emojiText = findViewById<TextView>(R.id.emojiTv)
        emojiText.alpha = 0f
        emojiText.translationY = 50f

        emojiText.animate()
            .alpha(1f)
            .translationY(0f)
            .setDuration(800)
            .setInterpolator(OvershootInterpolator())
            .start()
    }
}