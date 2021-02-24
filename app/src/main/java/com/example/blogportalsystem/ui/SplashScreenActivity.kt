package com.example.blogportalsystem.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.blogportalsystem.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var imagelogo:ImageView
    private lateinit var progressbar:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        imagelogo=findViewById(R.id.imagelogo)
        progressbar=findViewById(R.id.progressbar)

        CoroutineScope(Dispatchers.IO).launch{
            delay(1000)
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            finish()
        }

    }
}