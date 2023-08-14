package com.relevanx.tcom.activity.order

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.relevanx.tcom.BottomHelper
import com.relevanx.tcom.R
import com.relevanx.tcom.activity.home.HomeActivity
import com.relevanx.tcom.adapter.OrderActivityAdapter
import com.relevanx.tcom.api.OrderResponse
import com.relevanx.tcom.databinding.ActivityOrderBinding
import com.relevanx.tcom.utils.SavedPreference

class OrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderBinding
    private lateinit var viewModel: OrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val uid = SavedPreference.getUid(this)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[OrderViewModel::class.java]
        viewModel.isLoading.observe(this) { showLoading(it) }
        viewModel.order.observe(this) { setOrder(it) }

        if (uid != null) {
            viewModel.getOrderResponse(uid)
        }
//        setOrder(OrderDummyData.getDummyOrderList())

        bottom()
    }

    override fun onBackPressed() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun setOrder(dummyOrderList: OrderResponse) {
        binding.recylerOrder.layoutManager = LinearLayoutManager(this)
        binding.recylerOrder.adapter = OrderActivityAdapter(dummyOrderList)


    }
    private fun bottom() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.order
        BottomHelper.setupBottomNavigation(this)
    }

    private fun showLoading(state: Boolean) { binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE }
}