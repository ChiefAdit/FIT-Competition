package com.relevanx.tcom

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.relevanx.tcom.activity.profile.ProfileActivity
import com.relevanx.tcom.databinding.ActivitySettingsBinding


class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var workManager: WorkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.switchNotif.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {

                    val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    val notification: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext,
                        MyWorker.CHANNEL_ID
                    )
                        .setSmallIcon(androidx.core.R.drawable.notification_icon_background)
                        .setContentTitle(title)
                        .setContentText("Evos VS RRQ | 20:00")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setAutoCancel(true)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        val channel = NotificationChannel(MyWorker.CHANNEL_ID, MyWorker.CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
                        notification.setChannelId(MyWorker.CHANNEL_ID)
                        notificationManager.createNotificationChannel(channel)
                    }
                    notificationManager.notify(MyWorker.NOTIFICATION_ID, notification.build())
                }
            else {
            }
        })
    }



    override fun onBackPressed() {
        startActivity(Intent(this, ProfileActivity::class.java))
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle the back button press
        if (item.itemId == android.R.id.home) {
            // Go back to the profile activity
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}