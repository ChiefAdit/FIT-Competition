package com.relevanx.tcom.activity.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.relevanx.tcom.BottomHelper
import com.relevanx.tcom.EditProfileActivity
import com.relevanx.tcom.R
import com.relevanx.tcom.SettingsActivity
import com.relevanx.tcom.activity.home.HomeActivity
import com.relevanx.tcom.databinding.ActivityProfileBinding
import com.relevanx.tcom.activity.login.MainActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    lateinit var mGoogleSignInClient: GoogleSignInClient
        lateinit var adView: AdView
        lateinit var adRequest: AdRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading(true)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("46821013546-5eq2lb1tr4p3renfmhgmmibt1fs5d182.apps.googleusercontent.com")
            .requestEmail()
            .build()
        mGoogleSignInClient= GoogleSignIn.getClient(this,gso)

        val auth = Firebase.auth.currentUser

        showLoading(false)

        val profileImg = auth?.photoUrl.toString()
        Glide.with(this).load(profileImg).into(binding.profileImage)
        binding.emailProfile.text = auth?.email
        binding.nameProfile.text = auth?.displayName
        binding.phoneProfile.text = auth?.phoneNumber

        setAds()

        bottom()
    }

    private fun bottom() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.profile
        BottomHelper.setupBottomNavigation(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.profile_menu, menu)
        return true
    }

    fun setAds() {
        adView = binding.adView
        adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        adView.adListener = object: AdListener() {
            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }

            override fun onAdFailedToLoad(adError : LoadAdError) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle menu item clicks here
        when (item.itemId) {
            R.id.settings -> {
                // Handle Notifikasi click
                val intent= Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                finish()
//                return true
            }
            R.id.edit_profil -> {
                // Handle Edit Profil click
                val intent= Intent(this, EditProfileActivity::class.java)
                startActivity(intent)
                finish()
                return true
            }
            R.id.logout -> {
                // Handle Logout click
                mGoogleSignInClient.signOut().addOnCompleteListener {
                    val intent= Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showLoading(state: Boolean) { binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE }
}