package com.example.blogportalsystem.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.blogportalsystem.R
import com.example.blogportalsystem.model.Comment
import com.example.blogportalsystem.model.Reply
import com.example.blogportalsystem.repository.CommentRepository
import com.example.blogportalsystem.repository.ReplyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateReplyActivity : AppCompatActivity() {
    private var udid=""
    private var repid=""
    private lateinit var EtUpdateReply:EditText
    private lateinit var btnupdateReply:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_reply)

        btnupdateReply=findViewById(R.id.btnupdateReply)
        EtUpdateReply=findViewById(R.id.EtUpdateReply)


        val intent = intent;
        if(intent.extras!=null){
            val id = intent.getStringExtra("id");
            val rplybody = intent.getStringExtra("replybody");

            val userid=intent.getStringExtra("userID")
            repid="${id}"
            udid="$userid"
            EtUpdateReply.setText("$rplybody")

        }
        btnupdateReply.setOnClickListener {
            if(EtUpdateReply.text.isEmpty()){
                EtUpdateReply.error="Reply must not be empty!!"
            }
            else{
                updatereply()
            }
        }


    }
    private fun updatereply(){
        val etreply=EtUpdateReply.text.toString()
        val reply= Reply(replybody = etreply,userID = udid)
        CoroutineScope(Dispatchers.IO).launch {

            try
            {
                val replyRepository = ReplyRepository()
                val response = replyRepository.updatereply(repid,reply)

                if (response.success == true){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@UpdateReplyActivity, "updated Reply", Toast.LENGTH_SHORT).show()

                    }
                }
            }
            catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@UpdateReplyActivity, "$e", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}