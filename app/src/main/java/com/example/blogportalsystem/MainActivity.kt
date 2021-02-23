package com.example.blogportalsystem

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.blogportalsystem.api.ServiceBuilder
//import com.example.blogportalsystem.db.UserDB
import com.example.blogportalsystem.model.User
import com.example.blogportalsystem.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var edtemail: EditText
    private lateinit var edtpassword: EditText
    private lateinit var btnlogin: Button
    private lateinit var tvRegister: TextView
    private lateinit var linearLayout: LinearLayout
    private lateinit var chkremember:CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtemail = findViewById(R.id.edtemail)
        edtpassword = findViewById(R.id.edtpassword)
        btnlogin = findViewById(R.id.btnlogin)
        chkremember=findViewById(R.id.chkremember)

        tvRegister = findViewById(R.id.tvRegister)
        linearLayout = findViewById(R.id.linearLayout)
        getSharedPref()
        tvRegister.setOnClickListener {
            startActivity(Intent(this@MainActivity, SignUpActivity::class.java))
        }

        btnlogin.setOnClickListener {
            if(chkremember.isChecked){
                saveSharedPref()
            }
            login();

        }

    }

    private fun login() {
        val email = edtemail.text.toString()
        val password = edtpassword.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = UserRepository()
                val response = repository.checkUser(email, password)
                if (response.success == true) {
                    ServiceBuilder.token = "Bearer " + response.token
                    startActivity(
                        Intent(
                            this@MainActivity,
                            DashboardActivity::class.java
                        )
                    )
                    finish()
                } else {
                    withContext(Dispatchers.Main) {
                        val snack =
                            Snackbar.make(
                                linearLayout,
                                "Invalid credentials",
                                Snackbar.LENGTH_LONG
                            )
                        snack.setAction("OK", View.OnClickListener {
                            snack.dismiss()
                        })
                        snack.show()
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
    private fun saveSharedPref() {
         val email = edtemail.text.toString()
        val password = edtpassword.text.toString()
        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("email", email)
        editor.putString("password", password)
        editor.apply()
        Toast.makeText(
            this@MainActivity,
            "email and password saved",
            Toast.LENGTH_SHORT
        ).show()
    }
    private fun getSharedPref() {
        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
        val email = sharedPref.getString("email", "")
        val password = sharedPref.getString("password", "")
        edtemail.setText("$email")
        edtpassword.setText("$password")
    }
}