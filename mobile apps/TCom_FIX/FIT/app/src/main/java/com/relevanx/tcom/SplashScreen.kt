package com.relevanx.tcom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.relevanx.tcom.activity.home.HomeActivity
import com.relevanx.tcom.activity.login.MainActivity

class SplashScreen : AppCompatActivity() {
    private val splashTimeOut: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            // Pindah ke aktivitas berikutnya setelah waktu yang ditentukan selesai
            val intent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, splashTimeOut)
    }
}