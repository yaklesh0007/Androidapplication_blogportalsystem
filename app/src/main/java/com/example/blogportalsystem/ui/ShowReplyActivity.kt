package com.example.blogportalsystem.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blogportalsystem.R
import com.example.blogportalsystem.adapter.CommentAdapter
import com.example.blogportalsystem.adapter.ReplyAdapter
import com.example.blogportalsystem.api.ServiceBuilder
import com.example.blogportalsystem.model.Comment
import com.example.blogportalsystem.model.Reply
import com.example.blogportalsystem.repository.CommentRepository
import com.example.blogportalsystem.repository.ReplyRepository
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowReplyActivity : AppCompatActivity() {
    private lateinit var profile_image:CircleImageView
    private lateinit var TvUsername:TextView
    private lateinit var TvComment:TextView
    private lateinit var EtAddReply:EditText
    private lateinit var btnAddReply:Button
    private lateinit var replyRecyclerview:RecyclerView
    private var commentid=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_reply)
        TvUsername=findViewById(R.id.TvUsername)
        profile_image=findViewById(R.id.profile_image)
        TvComment=findViewById(R.id.TvComment)
        EtAddReply=findViewById(R.id.EtAddReply)
        btnAddReply=findViewById(R.id.btnAddReply)
        replyRecyclerview=findViewById(R.id.replyRecyclerview)

        val intent = intent;
        if(intent.extras!=null){
            val id = intent.getStringExtra("id");
            val cmtbody = intent.getStringExtra("commentBody");

            val username=intent.getStringExtra("username")
            val userimage=intent.getStringExtra("userimage")

            commentid="${id}"
            TvComment.text=cmtbody
            TvUsername.text=username

            userimage?.let{
                Glide.with(this)
                    .load(ServiceBuilder.loadImagePath() + it)
                    .into(profile_image)
            }

        }
        getallreplies()
        btnAddReply.setOnClickListener {
            if(EtAddReply.text.isEmpty()){
                Toast.makeText(this@ShowReplyActivity, "Comment must not be empty", Toast.LENGTH_SHORT).show()
            }
            else{
                addreply()
            }
        }
    }
    private fun getallreplies(){
        CoroutineScope(Dispatchers.IO).launch {

            try
            {
                val replyRepository = ReplyRepository()
                val response = replyRepository.getreplys(commentid)

                if (response.success == true){
                    withContext(Dispatchers.Main){
//                        Toast.makeText(context, "${response.data}", Toast.LENGTH_SHORT).show()
                        replyRecyclerview.adapter = ReplyAdapter(this@ShowReplyActivity!!,response.data!! as MutableList)
                        replyRecyclerview.layoutManager= LinearLayoutManager(this@ShowReplyActivity)
                    }
                }
            }
            catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@ShowReplyActivity, "$e", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun addreply(){
        val rplybody=EtAddReply.text.toString()
        val reply= Reply(replybody = rplybody)
        CoroutineScope(Dispatchers.IO).launch {

            try
            {
                val commentRepository = ReplyRepository()
                val response = commentRepository.insertreply(reply)

                if (response.success == true){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@ShowReplyActivity, "Added Reply", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@ShowReplyActivity, "$e", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}