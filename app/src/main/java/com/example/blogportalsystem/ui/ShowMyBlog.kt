package com.example.blogportalsystem.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blogportalsystem.R
import com.example.blogportalsystem.adapter.ShowMyPostAdapter
import com.example.blogportalsystem.model.Post
import com.example.blogportalsystem.repository.PostRepository
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowMyBlog : AppCompatActivity() {
    private lateinit var recyclerViewshow:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_my_blog)
        recyclerViewshow=findViewById(R.id.recyclerViewshow);
        showmypost()
    }
    private fun showmypost(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val blogrepository= PostRepository()
                val response=blogrepository.getmypost()
                if(response.success==true){
                    val lstblog =response.data
                    withContext(Dispatchers.Main){

                        recyclerViewshow.adapter = ShowMyPostAdapter(this@ShowMyBlog,response.data as MutableList<Post>)
                        recyclerViewshow.layoutManager = LinearLayoutManager(this@ShowMyBlog)
//                        Toast.makeText(this@ShowMyBlog, "$lstblog", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@ShowMyBlog, "${response.success}", Toast.LENGTH_SHORT).show()
                    }
                }

            }
            catch (ex:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@ShowMyBlog, "$ex", Toast.LENGTH_SHORT).show()
                }

            }
        }

    }
}