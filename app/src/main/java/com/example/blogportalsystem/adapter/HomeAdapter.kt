package com.example.blogportalsystem.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blogportalsystem.R
import com.example.blogportalsystem.api.ServiceBuilder
import com.example.blogportalsystem.model.PostWithUser
import com.example.blogportalsystem.repository.LikeRepository
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class HomeAdapter(
    val context:Context,
    var lsblog:List<PostWithUser>
):RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    class HomeViewHolder(view:View):RecyclerView.ViewHolder(view){
        val TvUsername: TextView
        val tvtitle: TextView
        val tvdescription: TextView
        val TvLikes: TextView
        val TvComments:TextView
        val profile_image:CircleImageView
        val imageSetting: TextView
        val imageview: ImageView
        val love:Button
        val btncomment:Button
        val btndislike:Button
        init {
            profile_image=view.findViewById(R.id.profile_image)
            TvUsername=view.findViewById(R.id.TvUsername)
            tvtitle=view.findViewById(R.id.tvtitle)
            tvdescription=view.findViewById(R.id.tvdescription)
            TvLikes=view.findViewById(R.id.TvLikes)
            imageSetting=view.findViewById(R.id.imageSetting)
            imageview=view.findViewById(R.id.imageview)
            TvComments=view.findViewById(R.id.TvComments)
            love=view.findViewById(R.id.love)
            btncomment=view.findViewById(R.id.btncomment)
            btndislike=view.findViewById(R.id.btndislike)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.blog,parent,false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        val blog=lsblog[position]
        holder.tvtitle.text=blog.title
        holder.tvdescription.text=blog.description
        holder.TvUsername.text=blog.userID?.username
        blog.image?.let {

            Glide.with(context!!)
                .load(ServiceBuilder.loadImagePath() + it)
                .into(holder.imageview)
        }
        blog.userID?.image?.let{
            Glide.with(context!!)
                .load(ServiceBuilder.loadImagePath() + it)
                .into(holder.profile_image)
        }
        holder.imageview.setOnClickListener {
            Toast.makeText(context, "${blog.image}", Toast.LENGTH_SHORT).show()
        }
        holder.love.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val likeRepositor=LikeRepository()
                    val responce=likeRepositor.addlike(blog._id)
                    if(responce.success==true){
                        withContext(Dispatchers.Main){
                            Toast.makeText(context, "${responce.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                catch (e:Exception){
                  withContext(Dispatchers.Main){
                      Toast.makeText(context, "$e", Toast.LENGTH_SHORT).show()
                  }
                }
            }
        }


    }

    override fun getItemCount(): Int {
        return lsblog.size
    }

}