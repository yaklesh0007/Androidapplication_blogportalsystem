package com.example.blogportalwear

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.blogportalwear.APIFORWEAR.ServiceBuilder
import com.example.blogportalwear.RepositoryForwear.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WearableDashboardActivity : WearableActivity() {
private lateinit var Imgwearprofile:ImageView
private lateinit var TvwearUsername:TextView
private lateinit var TvwearEmail:TextView
private lateinit var TvwearGender:TextView
private lateinit var BtnwearNotification:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wearable_dashboard)
        Imgwearprofile=findViewById(R.id.Imgwearprofile)
        TvwearUsername=findViewById(R.id.TvwearUsername)
        TvwearEmail=findViewById(R.id.TvwearEmail)
        TvwearGender=findViewById(R.id.TvwearGender)
        BtnwearNotification=findViewById(R.id.BtnwearNotification)
        loaduser()
        // Enables Always-on
        setAmbientEnabled()
    }
    private fun loaduser(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepository= UserRepository()
                val response=userRepository.showprofile()
                if (response.success==true){
                    val userData=response.data

                    withContext(Dispatchers.Main){

                        TvwearUsername.text="Username: "+userData!!.username
                        TvwearEmail.text="Email: "+userData!!.email

                        TvwearGender.text="Gender: "+userData!!.gender

                        userData.image?.let {

                            Glide.with(this@WearableDashboardActivity!!)
                                .load(ServiceBuilder.loadImagePath() + it)
                                .into(Imgwearprofile)
                        }
                        Toast.makeText(this@WearableDashboardActivity, "welcome to ${userData.username}", Toast.LENGTH_SHORT).show()

                    }
                }
                else{

                    Toast.makeText(this@WearableDashboardActivity, "your information was not found", Toast.LENGTH_SHORT).show()
                }
            }
            catch (ex:Exception){
                print(ex)
            }
        }

    }
}