package com.example.blogportalsystem.fragments

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blogportalsystem.R
import com.example.blogportalsystem.adapter.HomeAdapter
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

        FabBtnAdd.setOnClickListener {
            startActivity(Intent(activity,AddPostActivity::class.java))
        }
        if (checkNetwork(context!!)) {
            getallblog()
        }
        else if (!checkNetwork(context!!)) {
            getdatafromroom()
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
    private fun checkNetwork(context: Context): Boolean {
        val connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)

        return true
    }
    private fun getdatafromroom(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val  lsblog = BlogDB
                .getInstance(context!!)
                .getPostDAO()
                .getPost()
                withContext(Dispatchers.Main){
                    recyclerView.adapter = HomeAdapter(context!!,lsblog)
                    recyclerView.layoutManager= LinearLayoutManager(context)
                }

            }
            catch (err:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(context, "$err", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}