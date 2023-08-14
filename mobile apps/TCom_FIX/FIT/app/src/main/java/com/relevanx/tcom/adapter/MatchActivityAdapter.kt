package com.relevanx.tcom.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.relevanx.tcom.DetailMatchActivity
import com.relevanx.tcom.api.MatchesItem
import com.relevanx.tcom.databinding.ItemMatchBinding

class MatchActivityAdapter(private val listMatch: List<MatchesItem?>?) : RecyclerView.Adapter<MatchActivityAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchActivityAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMatchBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchActivityAdapter.ViewHolder, position: Int) {
        val matchItem = listMatch?.get(position)
        holder.bind(matchItem)
    }

    override fun getItemCount(): Int {
        return listMatch?.size ?: 0
    }

    inner class ViewHolder(private val binding: ItemMatchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(matchItem: MatchesItem?) {
            binding.namaTim1.text = matchItem?.teams?.get(0)?.name
            binding.namaTim2.text = matchItem?.teams?.get(1)?.name
            binding.skorTim1.text = matchItem?.teams?.get(0)?.point
            binding.skorTim2.text = matchItem?.teams?.get(1)?.point
            Glide.with(itemView).load(matchItem?.teams?.get(0)?.gambar).into(binding.photoTim1)
            Glide.with(itemView).load(matchItem?.teams?.get(1)?.gambar).into(binding.photoTim2)

            binding.root.setOnClickListener {
                val intent = Intent(itemView.context, DetailMatchActivity::class.java)
                intent.putExtra(DetailMatchActivity.EXTRA_MATCH, matchItem)
                itemView.context.startActivity(intent)
            }
        }

    }

}