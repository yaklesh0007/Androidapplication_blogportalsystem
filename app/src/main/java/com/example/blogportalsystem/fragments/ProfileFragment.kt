package com.example.blogportalsystem.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.blogportalsystem.R
import de.hdodenhof.circleimageview.CircleImageView


class ProfileFragment : Fragment() {
    private lateinit var ImgProfile:CircleImageView
    private lateinit var TvUsername:TextView
    private lateinit var TvEmail:TextView
    private lateinit var TvPhone:TextView
    private lateinit var TvGender:TextView
    private lateinit var BtnUpdateProfile:Button

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
        return view
    }

}