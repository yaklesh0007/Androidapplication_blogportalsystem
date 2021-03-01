package com.example.blogportalsystem.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.blogportalsystem.R
import com.example.blogportalsystem.ui.MapsActivity


class MoreFragment : Fragment() {

private lateinit var btnMap:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_more, container, false)
        btnMap=view.findViewById(R.id.btnMap)
        btnMap.setOnClickListener {
            startActivity(Intent(context,MapsActivity::class.java))
        }
        return view
    }


}