package com.example.blogportalwear

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : WearableActivity() {
 private lateinit var Etemail:EditText
 private lateinit var Etpassword:EditText
 private lateinit var Btnlogin:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Etemail=findViewById(R.id.Etemail)
        Etpassword=findViewById(R.id.Etpassword)
        Btnlogin=findViewById(R.id.Btnlogin)
        Btnlogin.setOnClickListener {
            var email=Etemail.text.toString()
            var password=Etpassword.text.toString()
            Toast.makeText(this@MainActivity, "$email+$password", Toast.LENGTH_SHORT).show()
        }

        // Enables Always-on
        setAmbientEnabled()
    }
}