package com.relevanx.tcom.activity.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.relevanx.tcom.api.NewsResponse
import com.relevanx.tcom.api.OrderResponse
import com.relevanx.tcom.api.TournamentResponse
import com.relevanx.tcom.BottomHelper
import com.relevanx.tcom.R
import com.relevanx.tcom.adapter.HomeActivityAdapter
import com.relevanx.tcom.adapter.HomeNewsAdapter
import com.relevanx.tcom.adapter.TournamentAdapter
import com.relevanx.tcom.databinding.ActivityHomeBinding
import com.relevanx.tcom.utils.SavedPreference

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel:HomeActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        uid = SavedPreference.getUid(this)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[HomeActivityViewModel::class.java]
        viewModel.isLoading.observe(this) { showLoading(it) }
        viewModel.news.observe(this) { setNews(it) }
        viewModel.isLoading2.observe(this) { showLoading(it) }
        viewModel.tournament.observe(this){ setTournament(it) }
        viewModel.isLoading3.observe(this) { showLoading(it) }
        viewModel.order.observe(this){ setActivity(it) }
        viewModel.login.observe(this){
            viewModel.getNews()
            viewModel.getTournament()
            viewModel.getOrderResponse(uid.toString())

        }
        viewModel.postLogin(uid.toString())

//        setNews(NewsDummyData.getDummyNewsList())
//        setActivity(ActivitiesDummyData.getDummyActivitiesList())
//        setTournament(TournamentDummyData.getDummyTournamentList())


        bottom()
    }

    fun setNews(newsList: NewsResponse) {
        binding.recylerNews.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recylerNews.adapter = HomeNewsAdapter(newsList)
    }

    fun setActivity(dummyActivitiesList: OrderResponse) {
        val ongoings = dummyActivitiesList.orders?.filter { it?.status == "ongoing" }
        binding.recylerActivity.layoutManager = LinearLayoutManager(this)
        binding.recylerActivity.adapter = HomeActivityAdapter(ongoings)
    }

    fun setTournament(dummyTournamentList: TournamentResponse) {
        binding.recylerRecomendation.layoutManager = LinearLayoutManager(this)
        binding.recylerRecomendation.adapter = TournamentAdapter(dummyTournamentList)
    }


    companion object {
        private var uid: String? = null
    }

    private fun bottom() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.home
        BottomHelper.setupBottomNavigation(this)
    }

    private fun showLoading(state: Boolean) { binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE }
}