package com.example.blogportalwear

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class WearableDashboardActivity : WearableActivity() {
private lateinit var Imgwearprofile:ImageView
private lateinit var TvwearUsername:TextView
private lateinit var TvwearEmail:TextView
private lateinit var TvwearGender:TextView
private lateinit var BtnwearNotification:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wearable_dashboard)
        Imgwearprofile=findViewById(R.id.Imgwearprofile)
        TvwearUsername=findViewById(R.id.TvwearUsername)
        TvwearEmail=findViewById(R.id.TvwearEmail)
        TvwearGender=findViewById(R.id.TvwearGender)
        BtnwearNotification=findViewById(R.id.BtnwearNotification)

        // Enables Always-on
        setAmbientEnabled()
    }
}