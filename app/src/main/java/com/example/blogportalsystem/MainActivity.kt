package com.example.blogportalsystem

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.blogportalsystem.model.User

class MainActivity : AppCompatActivity() {
    private lateinit var edtemail:EditText
    private lateinit var edtpassword:EditText
    private lateinit var btnlogin:Button
    private lateinit var btnSignup:Button
    var email:String=""
    var password:String=""
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
                startActivityForResult(Intent(this@MainActivity,SignUpActivity::class.java),1)
        }

        btnlogin.setOnClickListener {

            for (i:Int in lstUsers.indices){
                var em=edtemail.text.toString()
                var pwd=edtpassword.text.toString()
                email=lstUsers[i].UserEmail.toString()
                password=lstUsers[i].UserPassword.toString()

                if (em==email && pwd==password){
                        startActivity(Intent(this@MainActivity,DashboardActivity::class.java))
                }
                else
                {
                    Toast.makeText(this, "Invalid email password", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {

                lstUsers.add(data?.getParcelableExtra("data") as User)


            }
        }
    }


}