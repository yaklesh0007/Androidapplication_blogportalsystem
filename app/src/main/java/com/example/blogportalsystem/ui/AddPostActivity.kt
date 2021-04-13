package com.example.blogportalsystem.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import java.text.SimpleDateFormat
import java.util.*

import kotlin.Exception

class AddPostActivity : AppCompatActivity() {
    private lateinit var image:ImageView
    private lateinit var etTitle:EditText
    private lateinit var etdescription:EditText
    private lateinit var spinner:Spinner
    private lateinit var btnAdd:Button
    private var REQUEST_GALLERY_CODE = 0
    private var REQUEST_CAMERA_CODE = 1
    private var imageUrl: String? = null

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
        image.setOnClickListener {
            loadPopUpMenu()
        }
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
                    if ( imageUrl!=null)
                    {
                        uploadImage(response.data!!._id)
                    }
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@AddPostActivity,
                            "Post inserted successfully!!",
                            Toast.LENGTH_SHORT
                        ).show()

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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_GALLERY_CODE && data != null) {
                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val contentResolver = contentResolver
                val cursor =
                    contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
                cursor!!.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                imageUrl = cursor.getString(columnIndex)
                image.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
                cursor.close()
            } else if (requestCode == REQUEST_CAMERA_CODE && data != null) {
                val imageBitmap = data.extras?.get("data") as Bitmap
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val file = bitmapToFile(imageBitmap, "$timeStamp.jpg")
                imageUrl = file!!.absolutePath
                image.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
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

    private fun uploadImage(postID: String?) {
        if (imageUrl != null) {
            val file = File(imageUrl!!)
            val reqFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file)
            val body =
                MultipartBody.Part.createFormData("image", file.name, reqFile)
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val postRepository = PostRepository()
                    val response = postRepository.uploadImage(postID!!, body)
                    if (response.success == true) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@AddPostActivity, "Uploaded", Toast.LENGTH_SHORT)
                                .show()
                            startActivity(Intent(this@AddPostActivity,DashboardActivity::class.java))
                        }
                    }
                } catch (ex: java.lang.Exception) {
                    withContext(Dispatchers.Main) {
                        Log.d("Mero Error ", ex.localizedMessage)
                        Toast.makeText(
                            this@AddPostActivity,
                            ex.localizedMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}