package com.example.blogportalsystem.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.bumptech.glide.Glide
import com.example.blogportalsystem.R
import com.example.blogportalsystem.api.ServiceBuilder

class UpdateCommentActivity : AppCompatActivity() {
    private var userdid=""
    private var cmtid=""
    private lateinit var EtUpdateComment:EditText
    private lateinit var btnupdateComment:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_comment)

        val intent = intent;
        if(intent.extras!=null){
            val id = intent.getStringExtra("id");
            val cmtbody = intent.getStringExtra("commentBody");
            val username=intent.getStringExtra("username")
            val userid=intent.getStringExtra("userID")
            cmtid="${id}"
            userdid="$userid"
            EtUpdateComment.setText("$cmtbody")

        }
    }
}