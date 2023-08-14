package com.relevanx.tcom.activity.tournament

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.relevanx.tcom.activity.tournament.RegiterTournamentActivity
import com.relevanx.tcom.api.TournamenItem
import com.relevanx.tcom.databinding.ActivityDetailTournamentBinding

class DetailTournamentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTournamentBinding
    lateinit var adView: AdView
    lateinit var adRequest: AdRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTournamentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tournament = intent.getParcelableExtra<TournamenItem>(EXTRA_DATA)

        binding.titleTournament.text = tournament?.name
        binding.dateTournament.text = tournament?.tanggal
        binding.contentTournament.text = tournament?.description
        binding.feeTournament.text = tournament?.fee
        binding.phoneTournament.text = tournament?.whatsapp
//        binding.jumlahPemain.text = tournament?.partisipan.toString()
        binding.jumlahPemain.text = tournament?.id.toString()

        Glide.with(this).load(tournament?.gambar).into(binding.imageTournament)
        binding.btnRegisterTournament.setOnClickListener {
            Intent(this, RegiterTournamentActivity::class.java).also {
                it.putExtra(RegiterTournamentActivity.EXTRA_TOUR, tournament?.id)
                startActivity(it)
            }
        }

        setAds()

        binding.back.setOnClickListener {
            finish()
        }
    }

    private fun setAds() {
        adView = binding.adView
        adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}