package com.example.blogportalsystem.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.blogportalsystem.model.ReplyWithUser


class ReplyAdapter(
    val context: Context,
    var lsreply:List<ReplyWithUser>
) :RecyclerView.Adapter<ReplyAdapter.ReplyViewHolder>(){
    class ReplyViewHolder(view:View):RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReplyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ReplyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return lsreply.size
    }
}