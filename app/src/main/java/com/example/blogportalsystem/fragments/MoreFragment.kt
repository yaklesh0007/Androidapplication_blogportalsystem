package com.example.blogportalsystem.fragments

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.blogportalsystem.R
import com.example.blogportalsystem.ui.AddPostActivity
import com.example.blogportalsystem.ui.MainActivity
import com.example.blogportalsystem.ui.MapsActivity
import com.example.blogportalsystem.ui.ShowMyBlog
import kotlinx.coroutines.runBlocking


class MoreFragment : Fragment() {

private lateinit var btnMap:Button
private lateinit var btnshowmypost:Button
private lateinit var btnaddpost:Button
private lateinit var btnlogout:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_more, container, false)
        btnMap=view.findViewById(R.id.btnMap)
        btnshowmypost=view.findViewById(R.id.btnshowmypost)
        btnaddpost=view.findViewById(R.id.btnaddpost)
        btnlogout=view.findViewById(R.id.btnlogout)
        btnaddpost.setOnClickListener {
            startActivity(Intent(context,AddPostActivity::class.java))
        }


        btnMap.setOnClickListener {
            startActivity(Intent(context,MapsActivity::class.java))
        }
        btnshowmypost.setOnClickListener {
            startActivity(Intent(context,ShowMyBlog::class.java))
        }
        btnlogout.setOnClickListener {

            context?.getSharedPreferences("MyPref", MODE_PRIVATE)?.edit()?.clear()?.apply()
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(
                context,
                "Logout Successfully", Toast.LENGTH_SHORT
            ).show()
        }
        return view
    }





}