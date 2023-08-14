package com.relevanx.tcom.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.relevanx.tcom.activity.news.DetailNewsActivity
import com.relevanx.tcom.api.DataItem
import com.relevanx.tcom.api.NewsResponse
import com.relevanx.tcom.databinding.ItemNewsBinding

class HomeNewsAdapter(private val newsList: NewsResponse) : RecyclerView.Adapter<HomeNewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int){
        val newsItem = newsList.data?.get(position)
        holder.bind(newsItem)

    }

    override fun getItemCount(): Int {
        return newsList.data?.size ?: 0
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(newsItem: DataItem?){
            binding.titleNews.text = newsItem?.judul
            binding.newsAuthor.text = newsItem?.name
            binding.dateNews.text = newsItem?.tanggal
            binding.categoryNews.text = newsItem?.kategori
            Glide.with(itemView).load(newsItem?.gambar).into(binding.newsPhoto)

            binding.root.setOnClickListener {
                val intent = Intent(itemView.context, DetailNewsActivity::class.java)
                if (newsItem != null) {
                    intent.putExtra(DetailNewsActivity.EXTRA_NEWS_NAME, newsItem.judul)
                    intent.putExtra(DetailNewsActivity.EXTRA_NEWS_AUT, newsItem.name)
                    intent.putExtra(DetailNewsActivity.EXTRA_NEWS_DATE, newsItem.tanggal)
                    intent.putExtra(DetailNewsActivity.EXTRA_NEWS_CAT, newsItem.kategori)
                    intent.putExtra(DetailNewsActivity.EXTRA_NEWS_IMG, newsItem.gambar)
                    intent.putExtra(DetailNewsActivity.EXTRA_NEWS_DES, newsItem.isi)
                }
                itemView.context.startActivity(intent)
            }

        }
    }

}