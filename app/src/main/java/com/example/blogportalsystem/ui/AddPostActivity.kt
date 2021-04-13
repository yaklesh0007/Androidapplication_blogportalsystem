package com.example.blogportalsystem.ui

import android.content.Intent
import android.graphics.Bitmap
import android.media.session.MediaSession
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import com.example.blogportalsystem.R
import com.example.blogportalsystem.model.Post
import com.example.blogportalsystem.repository.PostRepository
import com.example.blogportalsystem.room.db.BlogDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

import kotlin.Exception

class AddPostActivity : AppCompatActivity() {
    private lateinit var image:ImageView
    private lateinit var etTitle:EditText
    private lateinit var etdescription:EditText
    private lateinit var spinner:Spinner
    private lateinit var btnAdd:Button
    private var itemselected=""
    private val catagories= arrayOf("IT","Social","Personal development","Science and technology",
    "Astrology","Political")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)
        image=findViewById(R.id.image)
        etTitle=findViewById(R.id.etTitle)
        etdescription=findViewById(R.id.etdescription)
        spinner=findViewById(R.id.spinner)
        btnAdd=findViewById(R.id.btnAdd)
        val adapter=ArrayAdapter(
            this,android.R.layout.simple_list_item_1,catagories
        )
        spinner.adapter=adapter
        spinner.onItemSelectedListener=
            object : AdapterView.OnItemSelectedListener
            {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    itemselected=p0?.getItemAtPosition(p2).toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        btnAdd.setOnClickListener {

            AddBlog()
        }

    }
    fun AddBlog(){
        val title=etTitle.text.toString()
        val description=etdescription.text.toString()
        val categoryID=itemselected
        var post= Post(title=title,description = description,categoryID = categoryID)
        CoroutineScope(Dispatchers.IO).launch {
            try {

                val blogrepository = PostRepository()
                val response = blogrepository.addBlog(post)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@AddPostActivity,
                            "Post inserted successfully!!",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(this@AddPostActivity,DashboardActivity::class.java))
                    }
                }
                else{
                    Toast.makeText(this@AddPostActivity, "${response.message}", Toast.LENGTH_SHORT).show()
                }
            }
            catch (e:Exception){
                Toast.makeText(this@AddPostActivity, "$e", Toast.LENGTH_SHORT).show()
            }
        }
    }


}