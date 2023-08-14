package com.relevanx.tcom.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.relevanx.tcom.activity.tournament.DetailTournamentActivity
import com.relevanx.tcom.api.TournamenItem
import com.relevanx.tcom.api.TournamentResponse
import com.relevanx.tcom.databinding.ItemTournamentBinding

class TournamentAdapter(private val listTournament: TournamentResponse) : RecyclerView.Adapter<TournamentAdapter.TournamentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TournamentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTournamentBinding.inflate(inflater, parent, false)
        return TournamentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TournamentViewHolder, position: Int) {
        val tournamentItem = listTournament.tournamen?.get(position)
        holder.bind(tournamentItem)
    }

    override fun getItemCount(): Int {
        return listTournament.tournamen?.size ?: 0
    }

    inner class TournamentViewHolder(private val binding: ItemTournamentBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tournamentItem: TournamenItem?) {
            binding.tournamentTitle.text = tournamentItem?.name
            binding.tournamentDate.text = tournamentItem?.tanggal
            binding.tournamentFee.text = tournamentItem?.fee
            Glide.with(itemView).load(tournamentItem?.gambar).into(binding.tournamentPhoto)

            binding.root.setOnClickListener {
                val intent = Intent(itemView.context, DetailTournamentActivity::class.java)
                intent.putExtra(DetailTournamentActivity.EXTRA_DATA, tournamentItem)
                itemView.context.startActivity(intent)
            }
        }
    }
}