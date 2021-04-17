package com.example.blogportalsystem.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.blogportalsystem.R

class UpdateReplyActivity : AppCompatActivity() {
    private var udid=""
    private var repid=""
    private lateinit var EtUpdateReply:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_reply)
        val intent = intent;
        if(intent.extras!=null){
            val id = intent.getStringExtra("id");
            val rplybody = intent.getStringExtra("replybody");

            val userid=intent.getStringExtra("userID")
            repid="${id}"
            udid="$userid"
            EtUpdateReply.setText("$rplybody")

        }
    }
    
}