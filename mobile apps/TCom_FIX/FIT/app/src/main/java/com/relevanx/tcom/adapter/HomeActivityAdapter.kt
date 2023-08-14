package com.relevanx.tcom.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.relevanx.tcom.activity.match.MatchActivity
import com.relevanx.tcom.api.OrdersItem
import com.relevanx.tcom.databinding.ItemActivityBinding

class HomeActivityAdapter(private val activitiesList: List<OrdersItem?>?) : RecyclerView.Adapter<HomeActivityAdapter.ActivitiesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivitiesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemActivityBinding.inflate(inflater, parent, false)
        return ActivitiesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActivitiesViewHolder, position: Int) {
        val activitiesItem = activitiesList?.get(position)
        holder.bind(activitiesItem)
    }

    override fun getItemCount(): Int {
        return activitiesList?.size ?:0
    }

    inner class ActivitiesViewHolder(private val binding: ItemActivityBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(activitiesItem: OrdersItem?){
            binding.activityTitle.text = activitiesItem?.name
            binding.activityDate.text = activitiesItem?.tanggal
            Glide.with(itemView).load(activitiesItem?.gambar).into(binding.activityPhoto)

            binding.root.setOnClickListener {
                Intent(itemView.context, MatchActivity::class.java).also {
                        it.putExtra(MatchActivity.EXTRA_ID, activitiesItem?.tournamentId)
                    itemView.context.startActivity(it)
                }
            }

        }
    }

}