package com.example.blogportalsystem

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.blogportalsystem.model.User

class SignUpActivity : AppCompatActivity() {
    private lateinit var edtfullname:EditText
    private lateinit var edtemail:EditText
    private lateinit var edtpassword:EditText
    private lateinit var edtphone:EditText
    private lateinit var edtimage:EditText
    private lateinit var btnSignup:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        edtfullname=findViewById(R.id.edtfullname)
        edtemail=findViewById(R.id.edtemail)
        edtpassword=findViewById(R.id.edtpassword)
        edtphone=findViewById(R.id.edtphone)
        edtimage=findViewById(R.id.edtimage)
        btnSignup=findViewById(R.id.btnSignup)

        btnSignup.setOnClickListener {
            var fullname=edtfullname.text.toString()
            var phone=edtphone.text.toString().toInt()
            var email=edtemail.text.toString()
            var password=edtpassword.text.toString()
            var image=edtimage.text.toString()

            var data:User= User(fullname,email,password,phone,image)

            var intent= Intent(this@SignUpActivity,MainActivity::class.java);
            intent.putExtra("data",data)
            setResult(Activity.RESULT_OK,intent)
            finish()
            edtfullname.text.clear()
            edtphone.text.clear()
            edtemail.text.clear()
            edtpassword.text.clear()
            edtimage.text.clear()
        }

    }
}