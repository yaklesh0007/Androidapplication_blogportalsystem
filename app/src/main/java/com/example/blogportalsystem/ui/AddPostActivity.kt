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
import java.lang.Exception

class AddPostActivity : AppCompatActivity() {
    private lateinit var image:ImageView
    private lateinit var etTitle:EditText
    private lateinit var etdescription:EditText
    private lateinit var spinner:Spinner
    private lateinit var btnAdd:Button
    private var itemselected=""
    private var REQUEST_GALLERY_CODE = 0
    private var REQUEST_CAMERA_CODE = 1
    private var imageUrl: String? = null
    private val catagories= arrayOf("IT","Health","Mass communication","Personal development","Social")
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
        val userID="6030e2c6993344261ca6db52"
        val categoryID=itemselected
        var post= Post(_id = "qweqry",title=title,description = description,userID = userID,categoryID = categoryID)
        CoroutineScope(Dispatchers.IO).launch {
            BlogDB
                .getInstance(this@AddPostActivity)
                .getPostDAO()
                .insertPost(post)

            // Switch to main thread
            withContext(Dispatchers.Main){
                Toast.makeText(this@AddPostActivity, "Post inserted successfully!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun loadPopUpMenu() {
        val popupMenu = PopupMenu(this@AddPostActivity, image)
        popupMenu.menuInflater.inflate(R.menu.gallerycameramenu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menuCamera ->
                    openCamera()
                R.id.menuGallery ->
                    openGallery()
            }
            true
        }
        popupMenu.show()
    }


    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_GALLERY_CODE)
    }
    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CAMERA_CODE)
    }
    private fun bitmapToFile(
        bitmap: Bitmap,
        fileNameToSave: String
    ): File? {
        var file: File? = null
        return try {
            file = File(
                getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                    .toString() + File.separator + fileNameToSave
            )
            file.createNewFile()
            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            val bitMapData = bos.toByteArray()
            //write the bytes in file
            val fos = FileOutputStream(file)
            fos.write(bitMapData)
            fos.flush()
            fos.close()
            file
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            file // it will return null
        }
    }

}