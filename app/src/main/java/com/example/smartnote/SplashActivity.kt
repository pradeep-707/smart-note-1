package com.example.smartnote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)

    Handler(Looper.getMainLooper()).postDelayed(
      {
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
      },
      2000
    )
  }
}
