package com.relevanx.tcom.activity.order

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.relevanx.tcom.activity.match.MatchActivity
import com.relevanx.tcom.api.OrdersItem
import com.relevanx.tcom.databinding.ActivityDetailOrderOnBinding


class DetailOrderOnActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailOrderOnBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailOrderOnBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val order = intent.getParcelableExtra<OrdersItem>(EXTRA_NAME)

        binding.titleTournament.text = order?.name
        binding.tanggal.text = order?.tanggal
        binding.nomorWhatsapp.text = order?.whatsapp
        binding.jumlahPemain.text = order?.partisipan.toString()
        binding.alamat.text = order?.lokasi.toString()

        Glide.with(this).load(order?.gambar).into(binding.imageTournament)

        binding.back.setOnClickListener {
            finish()
        }

        binding.gabungGroup.setOnClickListener{
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://chat.whatsapp.com/BLCmBB8RW9jFw05L6ZIggw"))
            startActivity(browserIntent)
        }

        binding.seeMatchButton.setOnClickListener {
            Intent(this, MatchActivity::class.java).also {
                it.putExtra(MatchActivity.EXTRA_ID, order?.tournamentId)
                startActivity(it)
            }
        }


    }

    companion object {
        const val EXTRA_NAME = "extra_name"
    }
}