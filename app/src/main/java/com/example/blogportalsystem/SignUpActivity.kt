package com.example.blogportalsystem

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.blogportalsystem.db.UserDB
import com.example.blogportalsystem.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpActivity : AppCompatActivity() {
    private lateinit var edtfullname: EditText
    private lateinit var edtemail: EditText
    private lateinit var edtpassword: EditText
    private lateinit var edtphone: EditText
    private lateinit var RdoGroup:RadioGroup
    private lateinit var RdoMale:RadioButton
    private lateinit var RdoFemale:RadioButton
    private lateinit var RdoOther:RadioButton
    private lateinit var btnSignup: Button
    private var userType:String="normaluser"
    var gender:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        edtfullname = findViewById(R.id.edtfullname)
        edtemail = findViewById(R.id.edtemail)
        edtpassword = findViewById(R.id.edtpassword)
        edtphone = findViewById(R.id.edtphone)
        RdoGroup=findViewById(R.id.RdoGroup)
        RdoMale=findViewById(R.id.RdoMale)
        RdoFemale=findViewById(R.id.RdoFemale)
        RdoOther=findViewById(R.id.RdoOther)
        btnSignup = findViewById(R.id.btnSignup)

        btnSignup.setOnClickListener {
            if (edtfullname.text.isEmpty()) {
                edtfullname.error = "Full name is required !!"
            } else if (edtemail.text.isEmpty()) {
                edtemail.error = "Email is required !!"
            } else if (edtpassword.text.isEmpty()) {
                edtpassword.error = "passord is required !!"
            } else if (edtphone.text.isEmpty()) {
                edtphone.error = "Phone number is required !!"
            } else {
                var fullname = edtfullname.text.toString()
                var phone = edtphone.text.toString()
                var email = edtemail.text.toString()
                var password = edtpassword.text.toString()
                when{
                    RdoMale.isChecked->{
                        gender="Male"
                    }
                    RdoFemale.isChecked->{
                        gender="Female"
                    }
                    RdoOther.isChecked->{
                        gender="Other"
                    }
                }

                val user = User(fullname, email, password, phone)

//                CoroutineScope(Dispatchers.IO).launch {
//                    UserDB
//                        .getInstance(this@SignUpActivity)
//                        .getUserDao()
//                        .registerUser(user)
//                    withContext(Main) {
//                        Toast.makeText(
//                            this@SignUpActivity,
//                            "User register Successfully",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
//                    }
//                }
                reset()

            }

        }

    }
    private  fun reset(){
        edtfullname.text.clear()
        edtphone.text.clear()
        edtemail.text.clear()
        edtpassword.text.clear()
    }
    private fun SaveUser(){

    }
}