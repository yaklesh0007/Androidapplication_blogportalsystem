package com.example.blogportalsystem.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blogportalsystem.model.CommentWithUser


class CommentAdapter(
    val context: Context,
    var lscomment:List<CommentWithUser>
) :RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(){
    class CommentViewHolder(view:View):RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return lscomment.size
    }
}