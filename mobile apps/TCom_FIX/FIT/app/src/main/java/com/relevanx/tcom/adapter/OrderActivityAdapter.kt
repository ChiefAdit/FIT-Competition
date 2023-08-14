package com.relevanx.tcom.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.relevanx.tcom.DetailOrderActivity
import com.relevanx.tcom.activity.home.HomeActivity
import com.relevanx.tcom.activity.order.DetailOrderOnActivity
import com.relevanx.tcom.api.OrderResponse
import com.relevanx.tcom.api.OrdersItem
import com.relevanx.tcom.databinding.ItemOrderBinding

class OrderActivityAdapter(private val listOrder: OrderResponse) : RecyclerView.Adapter<OrderActivityAdapter.OrderViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemOrderBinding.inflate(inflater, parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val orderItem = listOrder.orders?.get(position)
        holder.bind(orderItem)
    }

    override fun getItemCount(): Int {
        return listOrder.orders?.size ?: 0
    }

    inner class OrderViewHolder(private val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(orderItem: OrdersItem?) {
            binding.orderTitle.text = orderItem?.name
            binding.orderDate.text = orderItem?.tanggal
            binding.orderFee.text = orderItem?.fee
            binding.orderStatus.text = orderItem?.status
            if (orderItem != null) {
                binding.orderStatus.background = when(orderItem.status){
                    "ongoing" -> itemView.context.getDrawable(com.relevanx.tcom.R.color.green)
                    "pending" -> itemView.context.getDrawable(com.relevanx.tcom.R.color.red)
                    else -> itemView.context.getDrawable(com.relevanx.tcom.R.color.white)
                }
            }
            Glide.with(itemView).load(orderItem?.gambar).into(binding.orderPhoto)

            binding.root.setOnClickListener {
                if(orderItem?.status == "ongoing"){
                    Toast.makeText(itemView.context, "Order", Toast.LENGTH_SHORT).show()
                    val intent = Intent(itemView.context, DetailOrderOnActivity::class.java)
                    intent.putExtra(DetailOrderOnActivity.EXTRA_NAME, orderItem)
                    itemView.context.startActivity(intent)
                }else{
                    Toast.makeText(itemView.context, "Order masih pending", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}