package com.example.blogportalsystem.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.blogportalsystem.R
import com.example.blogportalsystem.adapter.CommentAdapter
import com.example.blogportalsystem.api.ServiceBuilder
import com.example.blogportalsystem.model.Comment
import com.example.blogportalsystem.repository.CommentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateCommentActivity : AppCompatActivity() {
    private var userdid=""
    private var cmtid=""
    private lateinit var EtUpdateComment:EditText
    private lateinit var btnupdateComment:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_comment)
        EtUpdateComment=findViewById(R.id.EtUpdateComment)
        btnupdateComment=findViewById(R.id.btnupdateComment)
        val intent = intent;
        if(intent.extras!=null){
            val id = intent.getStringExtra("id");
            val cmtbody = intent.getStringExtra("commentBody");

            val userid=intent.getStringExtra("userID")
            cmtid="${id}"
            userdid="$userid"
            EtUpdateComment.setText("$cmtbody")

        }
        btnupdateComment.setOnClickListener {
          if(EtUpdateComment.text.isEmpty()){
              Toast.makeText(this@UpdateCommentActivity, "Comment must not be empty", Toast.LENGTH_SHORT).show()
          }
            else{
                updateComment()
          }
        }
    }
    private fun updateComment(){
        val etcmt=EtUpdateComment.text.toString()
        val comment=Comment(commentBody = etcmt,userID = userdid)
        CoroutineScope(Dispatchers.IO).launch {

            try
            {
                val commentRepository = CommentRepository()
                val response = commentRepository.updatecomment(cmtid,comment)

                if (response.success == true){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@UpdateCommentActivity, "updated Comment", Toast.LENGTH_SHORT).show()

                    }
                }
            }
            catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@UpdateCommentActivity, "$e", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}