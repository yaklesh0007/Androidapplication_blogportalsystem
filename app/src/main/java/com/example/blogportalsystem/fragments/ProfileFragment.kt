package com.example.blogportalsystem.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.blogportalsystem.R
import com.example.blogportalsystem.api.ServiceBuilder
import com.example.blogportalsystem.repository.UserRepository
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {
    private lateinit var ImgProfile:CircleImageView
    private lateinit var TvUsername:TextView
    private lateinit var TvEmail:TextView
    private lateinit var TvPhone:TextView
    private lateinit var TvGender:TextView
    private lateinit var BtnUpdateProfile:Button
    private var REQUEST_GALLERY_CODE = 0
    private var REQUEST_CAMERA_CODE = 1
    private var imageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_profile, container, false)
        ImgProfile=view.findViewById(R.id.ImgProfile)
        TvUsername=view.findViewById(R.id.TvUsername)
        TvEmail=view.findViewById(R.id.TvEmail)
        TvPhone=view.findViewById(R.id.TvPhone)
        TvGender=view.findViewById(R.id.TvGender)
        BtnUpdateProfile=view.findViewById(R.id.BtnUpdateProfile)
        ImgProfile.setOnClickListener {

        }
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepository=UserRepository()
                val response=userRepository.showprofile()
                if (response.success==true){
                    val userData=response.data
                    TvUsername.text=userData?.username;
                    TvEmail.text=userData?.email;
                    TvPhone.text=userData?.phone;
                    TvGender.text=userData?.gender;
                    Glide.with(context!!)
                        .load(userData?.image)
                        .into(ImgProfile)
                }
                else{
                    Toast.makeText(context, "your information did not found", Toast.LENGTH_SHORT).show()
                }
            }
            catch (ex:Exception){

            }
        }
        return view
    }


}