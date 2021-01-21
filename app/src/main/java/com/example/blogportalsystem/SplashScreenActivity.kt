package com.example.blogportalsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.ProgressBar

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var imagelogo:ImageView
    private lateinit var progressbar:ProgressBar
    private val SplashTime:Long=3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        imagelogo=findViewById(R.id.imagelogo)
        progressbar=findViewById(R.id.progressbar)

        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },SplashTime)

    }
}