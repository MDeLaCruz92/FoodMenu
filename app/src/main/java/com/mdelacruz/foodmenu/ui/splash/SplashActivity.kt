package com.mdelacruz.foodmenu.ui.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mdelacruz.foodmenu.ui.onboarding.WelcomeActivity
import com.mdelacruz.foodmenu.util.start

class SplashActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    start(WelcomeActivity::class.java)
    finish()
  }
}