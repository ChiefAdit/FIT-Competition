package com.relevanx.tcom.activity.tournament

import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.relevanx.tcom.BottomHelper
import com.relevanx.tcom.R
import com.relevanx.tcom.activity.home.HomeActivity
import com.relevanx.tcom.adapter.TournamentAdapter
import com.relevanx.tcom.api.TournamentResponse
import com.relevanx.tcom.databinding.ActivityTournamentBinding

class TournamentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTournamentBinding
    private lateinit var viewModel: TournamentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTournamentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TournamentViewModel::class.java]
        viewModel.isLoading.observe(this) { showLoading(it) }
        viewModel.tournament.observe(this){ setTournamnet(it) }

        viewModel.getTournament("","","")

//        setTournamnet(TournamentDummyData.getDummyTournamentList())

        val filterImageView = findViewById<CardView>(R.id.filterCardView)
        filterImageView.setOnClickListener { view ->
            showFilterPopupMenu(view)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) {
                        viewModel.getTournament("",newText,"")
                    }
                    return false
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        viewModel.getTournament("",query,"")
                    }
                    return false
                }
            }
        )
        bottom()
    }

    override fun onBackPressed() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun setTournamnet(dummyTournamentList: TournamentResponse) {
        binding.recomendationRv.layoutManager = LinearLayoutManager(this)
        binding.recomendationRv.adapter = TournamentAdapter(dummyTournamentList)
    }

    private fun showFilterPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.filter_menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.sort_location -> {
                    viewModel.getTournament("salatiga","","")
                    true
                }
                R.id.sort_time -> {
                    viewModel.getTournament("","","")
                    true
                }
                R.id.sort_ml -> {
                    viewModel.getTournament("","","ml")
                    true
                }
                R.id.sort_pubg -> {
                    viewModel.getTournament("","","pubg")
                    true
                }
                R.id.sort_all -> {
                    viewModel.getTournament("","","")
                    true
                }

                // Tambahkan lebih banyak opsi filter jika diperlukan
                else -> false
            }
        }

        // Tampilkan PopupMenu
        popupMenu.show()
    }

    private fun bottom() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.tournament
        BottomHelper.setupBottomNavigation(this)
    }

    private fun showLoading(state: Boolean) { binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE }
}