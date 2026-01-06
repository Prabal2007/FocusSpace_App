package com.example.karamat // Ensure this matches your actual package name

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Inside MainActivity.kt
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val startButton = findViewById<Button>(R.id.btnStart)
        val centerGroup = findViewById<LinearLayout>(R.id.centerGroup)

        // INTERACTION: Animation when screen opens
        centerGroup.alpha = 0f
        centerGroup.animate().alpha(1f).setDuration(1000).start()

        // INTERACTION: Button "Squish" effect
        startButton.setOnTouchListener { v, event ->
            when (event.action) {
                android.view.MotionEvent.ACTION_DOWN -> {
                    v.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100).start()
                }
                android.view.MotionEvent.ACTION_UP -> {
                    v.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
                }
            }
            false // Set to false so the setOnClickListener still works!
        }

        startButton.setOnClickListener {
            val intent = Intent(this, TimerActivity::class.java)
            startActivity(intent)
        }

// ANIMATION: Fade in the title and logo
        centerGroup.alpha = 0f
        centerGroup.animate().alpha(1f).setDuration(1200).start()

// INTERACTION: Button scale effect when clicked
        startButton.setOnClickListener {
            startButton.animate().scaleX(0.9f).scaleY(0.9f).setDuration(100).withEndAction {
                startButton.animate().scaleX(1f).scaleY(1f).start()
                val intent = Intent(this, TimerActivity::class.java)
                startActivity(intent)
            }
        }
    }
}