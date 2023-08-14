package com.relevanx.tcom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.relevanx.tcom.api.MatchesItem
import com.relevanx.tcom.databinding.ActivityDetailMatchBinding
import com.relevanx.tcom.databinding.ActivityMatchBinding

class DetailMatchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMatchBinding
    lateinit var adView: AdView
    lateinit var adRequest: AdRequest


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val match = intent.getParcelableExtra<MatchesItem>(EXTRA_MATCH) as MatchesItem

//        val dummy = MatchDummyData.getDummyMatchList()[0]
//        binding.namaTim1.text = intent.getStringExtra("namaTim1")
        binding.namaTim1.text = match.teams?.get(0)?.name
        binding.namaTim2.text = match.teams?.get(1)?.name
        binding.skorTim1.text = match.teams?.get(0)?.point
        binding.skorTim2.text = match.teams?.get(1)?.point
        binding.matchDate.text = match.date
        binding.waktuMatch.text = match.time
        Glide.with(this).load(match.teams?.get(0)?.gambar).into(binding.photoTim1)
        Glide.with(this).load(match.teams?.get(1)?.gambar).into(binding.photoTim2)

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
        const val EXTRA_MATCH = "extra_match"
    }

}