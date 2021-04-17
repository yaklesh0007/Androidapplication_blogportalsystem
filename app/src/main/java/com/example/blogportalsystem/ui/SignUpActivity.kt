package com.example.blogportalsystem.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import com.example.blogportalsystem.R
//import com.example.blogportalsystem.db.UserDB
import com.example.blogportalsystem.model.User
import com.example.blogportalsystem.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var ImgProfile: ImageView
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
    private lateinit var TvSignUpTitle:TextView
    private lateinit var linearLayout: LinearLayout
    var gender: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        ImgProfile = findViewById(R.id.ImgProfile)
        edtfullname = findViewById(R.id.edtfullname)
        edtemail = findViewById(R.id.edtemail)
        edtpassword = findViewById(R.id.edtpassword)
        edtphone = findViewById(R.id.edtphone)
        RdoGroup = findViewById(R.id.RdoGroup)
        RdoMale = findViewById(R.id.RdoMale)
        RdoFemale = findViewById(R.id.RdoFemale)
        RdoOther = findViewById(R.id.RdoOther)
        TvSignUpTitle=findViewById(R.id.TvSignUpTitle)
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
                        startActivity(Intent(this@SignUpActivity,MainActivity::class.java))
                        finish()
                    }
                }
                else{
                    Toast.makeText(this@SignUpActivity, "${response.message}", Toast.LENGTH_SHORT).show()
                }
            } catch (ex: Exception) {
                withContext(Main) {

                    Snackbar.make(linearLayout, "$ex", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }




}