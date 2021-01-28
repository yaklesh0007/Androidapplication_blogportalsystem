package com.example.blogportalsystem

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.blogportalsystem.db.UserDB
import com.example.blogportalsystem.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var edtemail:EditText
    private lateinit var edtpassword:EditText
    private lateinit var btnlogin:Button
    private lateinit var btnSignup:Button

    var lstUsers= arrayListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtemail=findViewById(R.id.edtemail)
        edtpassword=findViewById(R.id.edtpassword)
        btnlogin=findViewById(R.id.btnlogin)
        btnSignup=findViewById(R.id.btnSignup)

        lstUsers= arrayListOf<User>()
        btnSignup.setOnClickListener {
                startActivity(Intent(this@MainActivity,SignUpActivity::class.java))
        }

        btnlogin.setOnClickListener {

            login();

        }

    }
    private fun login() {
        val email = edtemail.text.toString()
        val password = edtpassword.text.toString()
        var user: User? = null
        CoroutineScope(Dispatchers.IO).launch {
            user = UserDB
                .getInstance(this@MainActivity)
                .getUserDao()
                .checkUser(email,password)
            if(user == null){
                withContext(Main){
                    Toast.makeText(this@MainActivity, "Invalid credentials", Toast.LENGTH_SHORT)
                        .show()
                }
            }else{
                startActivity(Intent(this@MainActivity,
                    DashboardActivity::class.java))
            }
        }
    }
}