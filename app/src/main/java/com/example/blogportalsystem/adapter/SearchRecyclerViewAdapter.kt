package com.example.blogportalsystem.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blogportalsystem.model.Post

class SearchRecyclerViewAdapter
    (val context: Context,
     var lsblog:List<Post>):RecyclerView.Adapter<SearchRecyclerViewAdapter.SearchRecyclerViewHolder>(){
         class SearchRecyclerViewHolder(view: View):RecyclerView.ViewHolder(view){

         }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecyclerViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: SearchRecyclerViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return  lsblog.size
    }
}