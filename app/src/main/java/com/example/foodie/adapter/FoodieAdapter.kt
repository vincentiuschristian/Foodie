package com.example.foodie.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.DetailActivity
import com.example.foodie.data.Foodie
import com.example.foodie.databinding.ItemActivityBinding

class FoodieAdapter(
    private val listFood: ArrayList<Foodie>,
): RecyclerView.Adapter<FoodieAdapter.ListViewHolder>() {

    class ListViewHolder(var binding : ItemActivityBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listFood.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val(name, time, servings, photo) = listFood[position]
        holder.binding.TvName.text = name
        holder.binding.TvTime.text = time
        holder.binding.TvServing.text = servings
        holder.binding.foodImg.setImageResource(photo)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("key_food", listFood[holder.adapterPosition])
            holder.itemView.context.startActivity(intent)
        }
    }
}