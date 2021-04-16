package com.example.blogportalsystem.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.blogportalsystem.R
import com.example.blogportalsystem.model.User
import com.example.blogportalsystem.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateProfileActivity : AppCompatActivity() {
    private lateinit var Etupdatefullname:EditText
    private lateinit var EtupdatePhone:EditText
    private lateinit var EtupdatePassword:EditText
    private lateinit var EtupdateNewPassword:EditText
    private lateinit var EtupdateConfirmPassword:EditText
    private lateinit var btnUpdatePasswordinfo:Button
    private lateinit var btnUpdateProfileinfo:Button
    private lateinit var UpdateRdoGroup:RadioGroup
    private lateinit var UpdateRdoMale:RadioButton
    private lateinit var UpdateRdoFemale:RadioButton
    private lateinit var UpdateRdoOther:RadioButton
    var gender: String = ""
    private lateinit var linearLayoutforprofileupdate:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)
        linearLayoutforprofileupdate=findViewById(R.id.linearLayoutforprofileupdate)
        UpdateRdoOther=findViewById(R.id.UpdateRdoOther)
        UpdateRdoFemale=findViewById(R.id.UpdateRdoFemale)
        UpdateRdoMale=findViewById(R.id.UpdateRdoMale)
        UpdateRdoGroup=findViewById(R.id.UpdateRdoGroup)
        btnUpdateProfileinfo=findViewById(R.id.btnUpdateProfileinfo)
        btnUpdatePasswordinfo=findViewById(R.id.btnUpdatePasswordinfo)
        EtupdateConfirmPassword=findViewById(R.id.EtupdateConfirmPassword)
        EtupdateNewPassword=findViewById(R.id.EtupdateNewPassword)
        EtupdatePassword=findViewById(R.id.EtupdatePassword)
        EtupdatePhone=findViewById(R.id.EtupdatePhone)
        Etupdatefullname=findViewById(R.id.Etupdatefullname)
        getmyprofileinfo()
        btnUpdateProfileinfo.setOnClickListener {
            if (Etupdatefullname.text.isEmpty()) {
                Etupdatefullname.error = "Full name is required !!"
            } else if (EtupdatePhone.text.isEmpty()) {
                EtupdatePhone.error = "Phone is required !!"
            }

            else{
                upldateprofileinformation()
            }
        }
    }
    private fun getmyprofileinfo(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepository= UserRepository()
                val response=userRepository.showprofile()
                if (response.success==true){
                    val userData=response.data

                    withContext(Dispatchers.Main){
                        Etupdatefullname.setText("${userData!!.username}")
                        EtupdatePhone.setText("${userData!!.phone}")
                        if(userData.gender=="Male"){
                            UpdateRdoMale.isChecked=true
                        }
                        else if(userData.gender=="Female"){
                            UpdateRdoFemale.isChecked=true
                        }
                        else if(userData.gender=="Other"){
                            UpdateRdoOther.isChecked=true
                        }

                    }
                }
                else{

                    Toast.makeText(this@UpdateProfileActivity, "your information was not found", Toast.LENGTH_SHORT).show()
                }
            }
            catch (ex:Exception){
                print(ex)
            }
        }
    }
    private fun upldateprofileinformation(){
        var username = Etupdatefullname.text.toString()
        var phone = EtupdatePhone.text.toString()

        when {
            UpdateRdoMale.isChecked -> {
                gender = "Male"
            }
            UpdateRdoFemale.isChecked -> {
                gender = "Female"
            }
            UpdateRdoOther.isChecked -> {
                gender = "Other"
            }
        }
        val user = User(
            username = username,
            phone = phone,
            gender = gender

        )
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepository = UserRepository()
                val response = userRepository.updateprofileinfor(user)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {

                        val snackbar = Snackbar.make(
                            linearLayoutforprofileupdate,
                            "User Updated Successfully",
                            Snackbar.LENGTH_LONG
                        )
                        snackbar.show()
                        snackbar.setAction("Close", View.OnClickListener {
                            snackbar.dismiss()

                        })
                        startActivity(Intent(this@UpdateProfileActivity,DashboardActivity::class.java))
                    }
                }
                else{
                    Toast.makeText(this@UpdateProfileActivity, "${response.message}", Toast.LENGTH_SHORT).show()
                }

            }
            catch (e:Exception){
                print(e)
            }
        }
    }
}