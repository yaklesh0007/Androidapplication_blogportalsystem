package com.example.blogportalsystem.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blogportalsystem.R
import com.example.blogportalsystem.adapter.HomeAdapter
import com.example.blogportalsystem.model.Post
import com.example.blogportalsystem.room.db.BlogDB
import com.example.blogportalsystem.ui.AddPostActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeFragment : Fragment() {
    

    private lateinit var recyclerView:RecyclerView
    private lateinit var FabBtnAdd: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view=  inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView=view.findViewById(R.id.recyclerView)
        FabBtnAdd=view.findViewById(R.id.FabBtnAdd)
        recyclerView.layoutManager= LinearLayoutManager(context)
        CoroutineScope(Dispatchers.IO).launch {
          val  lsblog = BlogDB
                .getInstance(context!!)
                .getPostDAO()
                .getPost()

            val adapter=HomeAdapter(context!!,lsblog)
            recyclerView.adapter=adapter
        }

        FabBtnAdd.setOnClickListener {
            startActivity(Intent(activity,AddPostActivity::class.java))
        }
        return view
    }

}