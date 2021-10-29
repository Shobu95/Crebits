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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    private val TIME_OUT: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        CoroutineScope(Dispatchers.Main).launch {
            delay(TIME_OUT)
            finish()
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
        }

    }
}