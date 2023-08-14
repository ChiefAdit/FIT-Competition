package com.relevanx.tcom.activity.tournament

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.relevanx.tcom.activity.home.HomeActivity
import com.relevanx.tcom.api.TeamRequest
import com.relevanx.tcom.api.TournamenItem
import com.relevanx.tcom.databinding.ActivityDetailRegisterTournamentBinding
import com.relevanx.tcom.databinding.ActivityDetailTournamentBinding

class DetailRegisterTournament : AppCompatActivity() {
    private lateinit var binding: ActivityDetailRegisterTournamentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRegisterTournamentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val regis = intent.getParcelableExtra<TeamRequest>(EXTRA_NAME)

        binding.titleTournament.text = regis?.name
        binding.nomorWhatsapp.text = regis?.noTelp
        binding.contentTournament.text = regis?.alamat
        Glide.with(this).load(regis?.gambar).into(binding.imageTournament)

        binding.back.setOnClickListener {
            finish()
        }

    }

    override fun onBackPressed() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_IMAGE = "extra_image"
    }
}