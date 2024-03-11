package com.example.firebaserecruiting

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firebaserecruiting.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class CandidateAdapter(options:FirebaseRecyclerOptions<Candidate>):
    FirebaseRecyclerAdapter<Candidate, CandidateAdapter.MyViewHolder>(options){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater,parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: Candidate) {
        val c = holder.itemView.context
        val storageRef:StorageReference = FirebaseStorage.getInstance().getReferenceFromUrl(model.image)
        Glide.with(holder.imageItem.context).load(storageRef).into(holder.imageItem)
            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, CandidateDetailsActivity::class.java)

                // Pass data to the CandidateDetailsActivity
                intent.putExtra("name", model.name)
                intent.putExtra("title", model.title)
                intent.putExtra("description", model.description)
                intent.putExtra("image", model.image)

                holder.itemView.context.startActivity(intent)

        }
    }



    class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup):
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_candidate,parent,false)){
        val name: TextView = itemView.findViewById(R.id.name)
        val title: TextView = itemView.findViewById(R.id.texttitle)
        val imageItem: ImageView = itemView.findViewById(R.id.imageItem)
    }
}