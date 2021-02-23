package com.example.blogportalsystem

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
//import com.example.blogportalsystem.db.UserDB
import com.example.blogportalsystem.model.User
import com.example.blogportalsystem.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text

class SignUpActivity : AppCompatActivity() {
    private lateinit var edtfullname: EditText
    private lateinit var edtemail: EditText
    private lateinit var edtpassword: EditText
    private lateinit var edtphone: EditText
    private lateinit var RdoGroup: RadioGroup
    private lateinit var RdoMale: RadioButton
    private lateinit var RdoFemale: RadioButton
    private lateinit var RdoOther: RadioButton
    private lateinit var btnSignup: Button
    var userType: String = "normaluser"
    private lateinit var linearLayout: LinearLayout
    var gender: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        edtfullname = findViewById(R.id.edtfullname)
        edtemail = findViewById(R.id.edtemail)
        edtpassword = findViewById(R.id.edtpassword)
        edtphone = findViewById(R.id.edtphone)
        RdoGroup = findViewById(R.id.RdoGroup)
        RdoMale = findViewById(R.id.RdoMale)
        RdoFemale = findViewById(R.id.RdoFemale)
        RdoOther = findViewById(R.id.RdoOther)
        btnSignup = findViewById(R.id.btnSignup)
        linearLayout = findViewById(R.id.linearLayout)
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
                SaveUser()
                reset()
            }

        }

    }

    private fun reset() {
        edtfullname.text.clear()
        edtphone.text.clear()
        edtemail.text.clear()
        edtpassword.text.clear()
    }

    private fun SaveUser() {
        var username = edtfullname.text.toString()
        var phone = edtphone.text.toString()
        var email = edtemail.text.toString()
        var password = edtpassword.text.toString()
        when {
            RdoMale.isChecked -> {
                gender = "Male"
            }
            RdoFemale.isChecked -> {
                gender = "Female"
            }
            RdoOther.isChecked -> {
                gender = "Other"
            }
        }

        val user = User(
            username = username,
            email = email,
            password = password,
            phone = phone,
            gender = gender,
            userType = userType
        )
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepository = UserRepository()
                val response = userRepository.registerUser(user)
                if (response.success == true) {
                    withContext(Main) {

                        val snackbar = Snackbar.make(
                            linearLayout,
                            "User Register Successfully",
                            Snackbar.LENGTH_LONG
                        )
                        snackbar.show()
                        snackbar.setAction("Close", View.OnClickListener {
                            snackbar.dismiss()

                        })
                    }
                }
            } catch (ex: Exception) {
                withContext(Main) {

                    Snackbar.make(linearLayout, "$ex", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
}