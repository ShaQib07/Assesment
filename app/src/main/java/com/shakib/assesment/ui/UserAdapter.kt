package com.shakib.assesment.ui

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shakib.assesment.databinding.ItemUserBinding
import com.shakib.assesment.model.Data

class UserAdapter(private val  context: Context, private var userList: MutableList<Data>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    fun setData(newList: List<Data?>){
        for(index in newList.indices){
            userList.add(newList[index]!!)
        }
        Log.e("GSK" ,"Adapter Size : ${userList.size}")
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.binding.user = user
        holder.itemView.setOnClickListener { context.startActivity(Intent(context, DetailsActivity::class.java).putExtra("User", user)) }
    }

    class ViewHolder(itemView: ItemUserBinding) : RecyclerView.ViewHolder(itemView.root){
        var binding : ItemUserBinding = itemView
    }


}