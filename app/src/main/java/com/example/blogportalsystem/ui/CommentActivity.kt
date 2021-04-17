package com.example.blogportalsystem.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blogportalsystem.R
import com.example.blogportalsystem.adapter.CommentAdapter
import com.example.blogportalsystem.adapter.HomeAdapter
import com.example.blogportalsystem.api.ServiceBuilder
import com.example.blogportalsystem.model.Comment
import com.example.blogportalsystem.repository.CommentRepository
import com.example.blogportalsystem.repository.PostRepository
import com.example.blogportalsystem.room.db.BlogDB
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CommentActivity : AppCompatActivity() {
    private lateinit var profile_image:CircleImageView
    private lateinit var TvUsername:TextView
    private lateinit var imageSetting:TextView
    private lateinit var imageview:ImageView
    private lateinit var tvtitle:TextView
    private lateinit var tvdescription:TextView
    private lateinit var TvLikes:TextView
    private lateinit var TvComments:TextView
    private lateinit var EtAddComment:EditText
    private lateinit var btnAddComment:Button
    private lateinit var RecyclerViewComment:RecyclerView
    private var postID=""
    var userID=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        profile_image=findViewById(R.id.profile_image)
        imageview=findViewById(R.id.imageview)
        TvUsername=findViewById(R.id.TvUsername)
        imageSetting=findViewById(R.id.imageSetting)
        tvtitle=findViewById(R.id.tvtitle)
        tvdescription=findViewById(R.id.tvdescription)
        TvLikes=findViewById(R.id.TvLikes)
        TvComments=findViewById(R.id.TvComments)
        EtAddComment=findViewById(R.id.EtAddComment)
        btnAddComment=findViewById(R.id.btnAddComment)
        RecyclerViewComment=findViewById(R.id.RecyclerViewComment)

        val intent = intent;
        if(intent.extras!=null){
            val id = intent.getStringExtra("id");
            val title = intent.getStringExtra("title");
            val description=intent.getStringExtra("description")
            val uid=intent.getStringExtra("userID")
            val username=intent.getStringExtra("username")
            val userimage=intent.getStringExtra("userimage")
            val image=intent.getStringExtra("image")

            tvtitle.text=title
            TvUsername.text=username
            tvdescription.text=description
            image?.let {

                Glide.with(this)
                    .load(ServiceBuilder.loadImagePath() + it)
                    .into(imageview)
            }
            userimage?.let{
                Glide.with(this)
                    .load(ServiceBuilder.loadImagePath() + it)
                    .into(profile_image)
            }
            userID="$uid"
            postID="$id"
        }
        showallcomments ()
        btnAddComment.setOnClickListener {
            if(EtAddComment.text.isEmpty()){
                Toast.makeText(this@CommentActivity, "Comment must not be empty", Toast.LENGTH_SHORT).show()
            }
            else{
                addcomment()
            }
        }

    }
    fun showallcomments (){
        CoroutineScope(Dispatchers.IO).launch {

            try
            {
                val commentRepository = CommentRepository()
                val response = commentRepository.getcomments(postID)

                if (response.success == true){
                    withContext(Dispatchers.Main){
//                        Toast.makeText(context, "${response.data}", Toast.LENGTH_SHORT).show()
                        RecyclerViewComment.adapter = CommentAdapter(this@CommentActivity!!,response.data!! as MutableList)
                        RecyclerViewComment.layoutManager= LinearLayoutManager(this@CommentActivity)
                    }
                }
            }
            catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@CommentActivity, "$e", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun addcomment(){
        val cmtbody=EtAddComment.text.toString()
        val comment= Comment(commentBody =cmtbody,userID = userID,postID = postID )
        CoroutineScope(Dispatchers.IO).launch {

            try
            {
                val commentRepository = CommentRepository()
                val response = commentRepository.insertComment(comment)

                if (response.success == true){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@CommentActivity, "Added comment", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@CommentActivity, "$e", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}