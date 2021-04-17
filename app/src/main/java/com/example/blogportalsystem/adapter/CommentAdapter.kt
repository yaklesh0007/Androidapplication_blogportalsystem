package com.example.blogportalsystem.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blogportalsystem.R
import com.example.blogportalsystem.api.ServiceBuilder
import com.example.blogportalsystem.model.CommentWithUser
import com.example.blogportalsystem.repository.CommentRepository
import com.example.blogportalsystem.ui.ShowReplyActivity
import com.example.blogportalsystem.ui.UpdateCommentActivity
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class CommentAdapter(
    val context: Context,
    var lscomment:MutableList<CommentWithUser>
) :RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(){
    class CommentViewHolder(view:View):RecyclerView.ViewHolder(view){
        var TvUsername: TextView = view.findViewById(R.id.TvUsername)
        var TvComment: TextView = view.findViewById(R.id.TvComment)
        var profile_image: CircleImageView =view.findViewById(R.id.profile_image)
        var Actionverit: TextView =view.findViewById(R.id.Actionverit)
        var Actiondelete: TextView =view.findViewById(R.id.Actiondelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.comment,parent,false)
        return CommentViewHolder(view)

    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment=lscomment[position]
        holder.TvUsername.text=comment.userID?.username
        holder.TvComment.text=comment.commentBody
        holder.Actionverit.setOnClickListener {
            val intent = Intent(context, UpdateCommentActivity::class.java);
            intent.putExtra("id",comment._id);
            intent.putExtra("commentBody",comment.commentBody);
            intent.putExtra("userID",comment.userID?._id)
            context.startActivity(intent);
        }

        comment.userID?.image?.let{
            Glide.with(context!!)
                .load(ServiceBuilder.loadImagePath() + it)
                .into(holder.profile_image)
        }
        holder.TvComment.setOnClickListener {
            val intent = Intent(context, ShowReplyActivity::class.java);
            intent.putExtra("id",comment._id);
            intent.putExtra("commentBody",comment.commentBody);
            intent.putExtra("userID",comment.userID?._id)
            intent.putExtra("userimage",comment.userID?.image)
            intent.putExtra("username",comment.userID?.username)

            context.startActivity(intent);
        }
        holder.Actiondelete.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete Blog")
            builder.setMessage("Are You Sure You Want To Delete  ${comment.userID?.username} ?")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes") { _, _ ->

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val commentrepository = CommentRepository()
                        val response = commentrepository.deletecomment(comment?._id!!,comment?.userID?._id!!)

                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    context,
                                    "Comment Deleted",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                        withContext(Dispatchers.Main) {
                            lscomment.remove(comment)
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
        return lscomment.size
    }

}