package com.example.blogportalsystem.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blogportalsystem.R
import com.example.blogportalsystem.api.ServiceBuilder
import com.example.blogportalsystem.model.Post
import com.example.blogportalsystem.model.PostWithUser
import com.example.blogportalsystem.repository.PostRepository
import com.example.blogportalsystem.ui.UpdatePostActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ShowMyPostAdapter(
    var context: Context,
    var lstmyblogs:MutableList<Post>

) : RecyclerView.Adapter<ShowMyPostAdapter.ShowMyPostViewHolder>(){
    class ShowMyPostViewHolder(view:View):RecyclerView.ViewHolder(view){
        var tvtitle: TextView = view.findViewById(R.id.tvtitle)
        var image:ImageView=view.findViewById(R.id.image)
        var edit:ImageView=view.findViewById(R.id.edit)
        var delete:ImageView=view.findViewById(R.id.delete)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowMyPostViewHolder {
        var view= LayoutInflater.from(parent.context)
            .inflate(R.layout.viewshowmypost,parent,false)
        return ShowMyPostViewHolder(view)
    }
    override fun onBindViewHolder(holder: ShowMyPostViewHolder, position: Int) {
        var blog=lstmyblogs[position]
        holder.tvtitle.text=blog.title
        blog.image?.let {

            Glide.with(context!!)
                .load(ServiceBuilder.loadImagePath() + it)
                .into(holder.image)
        }
        holder.delete.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete Blog")
            builder.setMessage("Are You Sure You Want To Delete  ${blog.userID} ?")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes") { _, _ ->

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val postRepository = PostRepository()
                        val response = postRepository.deleteblog(blog?._id!!,blog.userID!!)

                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    context,
                                    "blog Deleted",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                        withContext(Dispatchers.Main) {
                            lstmyblogs.remove(blog)
                            notifyDataSetChanged()
                        }

                    }catch (ex: Exception){
                        withContext(Dispatchers.Main){
                            Toast.makeText(context,
                                ex.toString(),
                                Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }


            builder.setNegativeButton("No") { _, _ ->
                Toast.makeText(context, "Action Cancelled", Toast.LENGTH_SHORT).show()
            }

            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
        holder.edit.setOnClickListener {
            val intent = Intent(context, UpdatePostActivity::class.java);
            intent.putExtra("id",blog._id);
            intent.putExtra("title",blog.title);
            intent.putExtra("description",blog.description)
            intent.putExtra("userID",blog.userID)
            intent.putExtra("category",blog.categoryID)
            context.startActivity(intent);
        }

    }

    override fun getItemCount(): Int {
        return lstmyblogs.size
    }
}


