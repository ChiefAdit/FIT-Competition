package com.relevanx.tcom.activity.match

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.relevanx.tcom.adapter.MatchActivityAdapter
import com.relevanx.tcom.api.MatchesItem
import com.relevanx.tcom.databinding.ActivityMainBinding
import com.relevanx.tcom.databinding.ActivityMatchBinding

class MatchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMatchBinding
    private lateinit var viewModel: MatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MatchViewModel::class.java]

        val id = intent.getIntExtra(EXTRA_ID, 0)

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.setLayerType(WebView.LAYER_TYPE_HARDWARE, null)
        binding.webView.webChromeClient = WebChromeClient()

        viewModel.match.observe(this) {
            setMatch(it.matches)
            val web = it.matches?.get(0)?.bracket
            if (web != null) {
                binding.webView.loadUrl(web)
            }
        }

        viewModel.getMatchResponse(id)

//        setMatch(MatchDummyData.getDummyMatchList())

        binding.back.setOnClickListener {
            finish()
        }

    }

    private fun setMatch(dummyMatchList: List<MatchesItem?>?) {
        binding.rvMatch.layoutManager = LinearLayoutManager(this)
        binding.rvMatch.adapter = MatchActivityAdapter(dummyMatchList)
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private fun showLoading(state: Boolean) { binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE }
}