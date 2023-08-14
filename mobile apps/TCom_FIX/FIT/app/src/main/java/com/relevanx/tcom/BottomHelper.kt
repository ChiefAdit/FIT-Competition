package com.relevanx.tcom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.relevanx.tcom.activity.home.HomeActivity
import com.relevanx.tcom.activity.order.OrderActivity
import com.relevanx.tcom.activity.profile.ProfileActivity
import com.relevanx.tcom.activity.tournament.TournamentActivity

class BottomHelper {
    companion object {
        fun setupBottomNavigation(activity: AppCompatActivity) {

            val bottomNavigationView =
                activity.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.home -> {
                        // Handle Home menu item click
                        if (activity !is HomeActivity) {
                            activity.startActivity(Intent(activity, HomeActivity::class.java))
                            activity.finish()
                        }
                        true
                    }

                    R.id.tournament -> {
                        // Handle History menu item click
                        if (activity !is TournamentActivity) {
                            activity.startActivity(Intent(activity, TournamentActivity::class.java))
                            activity.finish()
                        }
                        true
                    }

                    R.id.order -> {
                        // Handle Task menu item click
                        if (activity !is OrderActivity) {
                            activity.startActivity(Intent(activity, OrderActivity::class.java))
                            activity.finish()
                        }
                        true
                    }

                    R.id.profile -> {
                        if (activity !is ProfileActivity) {
                            activity.startActivity(Intent(activity, ProfileActivity::class.java))
                            activity.finish()
                        }
                        true
                    }

                    else -> false
                }
            }
        }
    }
}
