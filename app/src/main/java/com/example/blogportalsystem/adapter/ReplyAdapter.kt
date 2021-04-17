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

import com.example.blogportalsystem.model.ReplyWithUser
import com.example.blogportalsystem.repository.PostRepository
import com.example.blogportalsystem.ui.UpdatePostActivity
import com.example.blogportalsystem.ui.UpdateReplyActivity
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class ReplyAdapter(
    val context: Context,
    var lsreply:MutableList<ReplyWithUser>
) :RecyclerView.Adapter<ReplyAdapter.ReplyViewHolder>(){
    class ReplyViewHolder(view:View):RecyclerView.ViewHolder(view){
        var TvUsername: TextView = view.findViewById(R.id.TvUsername)
        var TvReply: TextView = view.findViewById(R.id.TvReply)
        var profile_image: CircleImageView =view.findViewById(R.id.profile_image)
        var Actionverit: TextView =view.findViewById(R.id.Actionverit)
        var Actiondelete: TextView =view.findViewById(R.id.Actiondelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReplyViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.comment,parent,false)
        return ReplyAdapter.ReplyViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReplyViewHolder, position: Int) {
        val reply=lsreply[position]
        holder.TvUsername.text=reply.userID?.username
        holder.TvReply.text=reply.replybody
        holder.Actionverit.setOnClickListener {
            val intent = Intent(context, UpdateReplyActivity::class.java);
            intent.putExtra("id",reply._id);
            intent.putExtra("replybody",reply.replybody);
            intent.putExtra("userID",reply.userID?._id)

            context.startActivity(intent);
        }
        reply.userID?.image?.let{
            Glide.with(context!!)
                .load(ServiceBuilder.loadImagePath() + it)
                .into(holder.profile_image)
        }

//        holder.Actiondelete.setOnClickListener {
//            val builder = AlertDialog.Builder(context)
//            builder.setTitle("Delete Reply")
//            builder.setMessage("Are You Sure You Want To Delete  ${reply.userID?.username} ?")
//            builder.setIcon(android.R.drawable.ic_dialog_alert)
//            builder.setPositiveButton("Yes") { _, _ ->
//
//                CoroutineScope(Dispatchers.IO).launch {
//                    try {
//                        val postRepository = Reply()
//                        val response = postRepository.deleteblog(reply?._id!!,reply.userID!!)
//
//                        if (response.success == true) {
//                            withContext(Dispatchers.Main) {
//                                Toast.makeText(
//                                    context,
//                                    "blog Deleted",
//                                    Toast.LENGTH_SHORT
//                                )
//                                    .show()
//                            }
//                        }
//                        withContext(Dispatchers.Main) {
//                            lsreply.remove(reply)
//                            notifyDataSetChanged()
//                        }
//
//                    }catch (ex: Exception){
//                        withContext(Dispatchers.Main){
//                            Toast.makeText(context,
//                                ex.toString(),
//                                Toast.LENGTH_SHORT)
//                                .show()
//                        }
//                    }
//                }
//            }
//
//
//            builder.setNegativeButton("No") { _, _ ->
//                Toast.makeText(context, "Action Cancelled", Toast.LENGTH_SHORT).show()
//            }
//
//            val alertDialog: AlertDialog = builder.create()
//            alertDialog.setCancelable(false)
//            alertDialog.show()
//        }
    }

    override fun getItemCount(): Int {
        return lsreply.size
    }
}