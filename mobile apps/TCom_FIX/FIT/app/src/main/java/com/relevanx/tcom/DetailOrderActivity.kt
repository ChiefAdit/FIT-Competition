package com.relevanx.tcom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.relevanx.tcom.databinding.ActivityDetailTournamentBinding

class DetailOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTournamentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTournamentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }



    companion object {
        const val EXTRA_ID = "extra_id"
    }
}