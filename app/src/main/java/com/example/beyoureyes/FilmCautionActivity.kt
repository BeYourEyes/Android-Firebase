package com.example.beyoureyes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FilmCautionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_caution)

        val filmStartButton : Button = findViewById(R.id.filmStartButton)

        filmStartButton.setOnClickListener {
            intent = Intent(this, ScanSuccessActivity::class.java)
            startActivity(intent)
        }

    }
}