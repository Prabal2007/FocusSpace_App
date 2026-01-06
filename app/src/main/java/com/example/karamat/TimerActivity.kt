package com.example.karamat // Ensure this matches your package name

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TimerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        val timerText = findViewById<TextView>(R.id.tvTimer)
        val btnCancel = findViewById<Button>(R.id.btnCancel)

        // 1. Setup the 1-Minute Timer (60,000 ms)
        val timer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000

                // Update the text to show seconds
                timerText.text = String.format("00:%02d", secondsRemaining)

                // INTERACTIVITY: Subtle "Pulse" animation every second
                timerText.animate()
                    .scaleX(1.1f)
                    .scaleY(1.1f)
                    .setDuration(100)
                    .withEndAction {
                        timerText.animate().scaleX(1.0f).scaleY(1.0f).setDuration(100).start()
                    }.start()
            }

            override fun onFinish() {
                // When 1 minute is over, move to the Success screen
                val intent = Intent(this@TimerActivity, SuccessActivity::class.java)
                startActivity(intent)
                finish() // Close the timer screen so they can't go back
            }
        }

        // Start the timer automatically
        timer.start()

        // Cancel button logic
        btnCancel.setOnClickListener {
            timer.cancel()
            finish()
        }
    }
}