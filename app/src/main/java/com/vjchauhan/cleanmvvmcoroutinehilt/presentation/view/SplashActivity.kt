package com.vjchauhan.cleanmvvmcoroutinehilt.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.vjchauhan.cleanmvvmcoroutinehilt.R
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        splashScreen.setKeepOnScreenCondition{true  }
        Timer().schedule(1500){
            startActivity(Intent(this@SplashActivity,MainActivity::class.java))
        }
    }
}