package com.example.blogportalsystem.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blogportalsystem.R
import com.example.blogportalsystem.adapter.HomeAdapter
import com.example.blogportalsystem.model.Post
import com.example.blogportalsystem.repository.PostRepository
import com.example.blogportalsystem.room.db.BlogDB
import com.example.blogportalsystem.ui.AddPostActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*


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

        val adapter=HomeAdapter(context!!, emptyList())
        recyclerView.layoutManager=LinearLayoutManager(requireContext())
        recyclerView.adapter=adapter
        getallblog()
        FabBtnAdd.setOnClickListener {
            startActivity(Intent(activity,AddPostActivity::class.java))
        }
        return view
    }

    private fun getallblog(){
        CoroutineScope(Dispatchers.IO).launch {
//          val  lsblog = BlogDB
//                .getInstance(context!!)
//                .getPostDAO()
//                .getPost()
//
//            val adapter=HomeAdapter(context!!,lsblog)
//            recyclerView.adapter=adapter
            try
            {
                val blogrepository = PostRepository()
                val response = blogrepository.getallBlog()
                BlogDB
                    .getInstance(context!!)
                    .getPostDAO()
                    .insertPost(response.data!!)
                if (response.success == true){
                    withContext(Dispatchers.Main){
                        recyclerView.adapter = HomeAdapter(context!!,response.data!!)
                        recyclerView.layoutManager= LinearLayoutManager(context)
                    }
                }
            }
            catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(context, "$e", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}