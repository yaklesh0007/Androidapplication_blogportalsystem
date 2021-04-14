package com.example.blogportalsystem.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.blogportalsystem.R
import com.example.blogportalsystem.model.Post
import com.example.blogportalsystem.model.User
import com.example.blogportalsystem.repository.PostRepository
import com.example.blogportalsystem.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_post.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdatePostActivity : AppCompatActivity() {
    private lateinit var EtUpdateTitle:EditText
    private lateinit var EtUpdatedescription:EditText
    private lateinit var btnUpdatePost:Button
    private lateinit var linerLayoutBlog:LinearLayout
    private lateinit var Updatespinner:Spinner
    private var itemselected=""
    var userID=""
    var id=""
    private val catagories= arrayOf("IT","Social","Personal development","Science and technology",
        "Astrology","Political")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_post)
        EtUpdateTitle=findViewById(R.id.EtUpdateTitle)
        EtUpdatedescription=findViewById(R.id.EtUpdatedescription)
        btnUpdatePost=findViewById(R.id.btnUpdatePost)
        linerLayoutBlog=findViewById(R.id.linerLayoutBlog)
        Updatespinner=findViewById(R.id.Updatespinner)
        val intent = intent;
        if(intent.extras!=null){
            val postid = intent.getStringExtra("id");
            val title = intent.getStringExtra("title");
            val description=intent.getStringExtra("description")
            val user=intent.getStringExtra("userID")
            val category=intent.getStringExtra("category")
            EtUpdateTitle.setText("$title")
            EtUpdatedescription.setText("$description")
            itemselected="$category"
            userID="$user"
            id="$postid"
        }
        val adapter=ArrayAdapter(
            this,android.R.layout.simple_list_item_1,catagories
        )
        Updatespinner.adapter=adapter
        Updatespinner.onItemSelectedListener=
            object : AdapterView.OnItemSelectedListener
            {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    itemselected=p0?.getItemAtPosition(p2).toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        btnUpdatePost.setOnClickListener {
            if (EtUpdateTitle.text.isEmpty()) {
                EtUpdateTitle.error = "title is required !!"
            } else if (EtUpdatedescription.text.isEmpty()) {
                EtUpdatedescription.error = "description is required !!"
            }

            else{
                upldatebloginformation()
            }
        }
    }

    private fun upldatebloginformation(){


        val updatedtitle=EtUpdateTitle.text.toString()
        val updateddescription=EtUpdatedescription.text.toString()
        val blog = Post(
            title = updatedtitle,
            description = updateddescription,
            categoryID = itemselected,
            userID = userID

        )
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val postRepository = PostRepository()
                val response = postRepository.blogupdate(blog,id)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {

                        val snackbar = Snackbar.make(
                            linerLayoutBlog,
                            "Blog Updated Successfully",
                            Snackbar.LENGTH_LONG
                        )
                        snackbar.show()
                        snackbar.setAction("Close", View.OnClickListener {
                            snackbar.dismiss()

                        })
                        startActivity(Intent(this@UpdatePostActivity,DashboardActivity::class.java))
                    }
                }
                else{
                    Toast.makeText(this@UpdatePostActivity, "${response.message}", Toast.LENGTH_SHORT).show()
                }

            }
            catch (e:Exception){
                print(e)
            }
        }
    }


}