package com.example.blogportalwear

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.blogportalwear.APIFORWEAR.ServiceBuilder
import com.example.blogportalwear.RepositoryForwear.UserRepository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
            login()
        }

        // Enables Always-on
        setAmbientEnabled()
    }
    private fun login() {
        var email=Etemail.text.toString()
        var password=Etpassword.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = UserRepository()
                val response = repository.checkUser(email, password)
                if (response.success == true) {
                    ServiceBuilder.token = "Bearer " + response.token
                    startActivity(
                        Intent(
                            this@MainActivity,
                            WearableDashboardActivity::class.java
                        )
                    )
                    finish()

                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@MainActivity, "Invalid Creadential", Toast.LENGTH_SHORT).show()
                    }
                }

            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@MainActivity,
                        "$ex", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}