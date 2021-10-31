package com.shobu95.crebits

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shobu95.crebits.R
import android.view.WindowManager
import android.os.Looper
import android.content.Intent
import android.graphics.Color
import android.os.Handler
import com.shobu95.crebits.screens.MainActivity
import kotlinx.coroutines.*

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var activityScope: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //Initializing coroutine scope
        activityScope = launchSplashScope()

    }

    override fun onPause() {
        super.onPause()
        activityScope.cancel()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        activityScope.cancel()
    }

    override fun onResume() {
        super.onResume()
        if (activityScope.isActive.not())
            launchSplashScope()
    }

    override fun onDestroy() {
        super.onDestroy()
        activityScope.cancel()
    }

    private fun launchSplashScope() =
        CoroutineScope(Dispatchers.Main).launch {
            delay(TIME_OUT)
            finish()
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
        }

    companion object {
        private const val TIME_OUT: Long = 3000
    }
}