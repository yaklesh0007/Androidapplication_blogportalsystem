package com.example.blogportalsystem.adapter

import android.app.AlertDialog
import android.content.Context
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
import com.example.blogportalsystem.model.PostWithUser
import com.example.blogportalsystem.repository.PostRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ShowMyPostAdapter(
    val context: Context,
    var lstmyblogs:MutableList<PostWithUser>

) : RecyclerView.Adapter<ShowMyPostAdapter.ShowMyPostViewHolder>(){
    class ShowMyPostViewHolder(view:View):RecyclerView.ViewHolder(view){
        val tvtitle: TextView = view.findViewById(R.id.tvtitle)
        val image:ImageView=view.findViewById(R.id.image)
        val edit:ImageView=view.findViewById(R.id.edit)
        val delete:ImageView=view.findViewById(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowMyPostViewHolder {

        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.viewshowmypost,parent,false)
        return ShowMyPostAdapter.ShowMyPostViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowMyPostViewHolder, position: Int) {

        val blog=lstmyblogs[position]
        holder.tvtitle.text=blog.title
        blog.image?.let {

            Glide.with(context!!)
                .load(ServiceBuilder.loadImagePath() + it)
                .into(holder.image)
        }

        holder.delete.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete Student")
            builder.setMessage("Are You Sure You Want To Delete  ${blog.userID?.username} ?")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes") { _, _ ->

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val postRepository = PostRepository()
                        val response = postRepository.deleteblog(blog?._id!!,blog.userID?._id!!)
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

    }

    override fun getItemCount(): Int {
        return lstmyblogs.size
    }
}


