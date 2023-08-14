package com.relevanx.tcom.activity.news

import android.content.Intent
import android.nfc.NfcAdapter.EXTRA_DATA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.relevanx.tcom.R
import com.relevanx.tcom.activity.login.MainActivity
import com.relevanx.tcom.api.TournamenItem
import com.relevanx.tcom.databinding.ActivityDetailNewsBinding

class DetailNewsActivity : AppCompatActivity() {
    lateinit var adView: AdView
    lateinit var adRequest: AdRequest

    private lateinit var binding: ActivityDetailNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val newsTitle = intent.getStringExtra(EXTRA_NEWS_NAME)
        val newsAuthor = intent.getStringExtra(EXTRA_NEWS_AUT)
        val newsDate = intent.getStringExtra(EXTRA_NEWS_DATE)
//        val newsCategory = intent.getStringExtra(EXTRA_NEWS_CAT)
        val newsImage = intent.getStringExtra(EXTRA_NEWS_IMG)
        val newsContent = intent.getStringExtra(EXTRA_NEWS_DES)

        binding.titleNews.text = newsTitle
        binding.contentNews.text = newsContent
        binding.authorNews.text = newsAuthor
        binding.dateNews.text = newsDate
//        binding.categoryNews.text = newsCategory
        Glide.with(this).load(newsImage).into(binding.imageNews)


        binding.back.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }

        setAds()

    }

    private fun setAds() {
        adView = binding.adView
        adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    companion object {
        const val EXTRA_NEWS_NAME = "extra_news_name"
        const val EXTRA_NEWS_DES = "extra_news_des"
        const val EXTRA_NEWS_CAT = "extra_news_cat"
        const val EXTRA_NEWS_DATE = "extra_news_date"
        const val EXTRA_NEWS_IMG = "extra_news_img"
        const val EXTRA_NEWS_AUT = "extra_news_aut"
    }
}