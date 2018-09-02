package com.mdelacruz.foodmenu.ui.onboarding

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.mdelacruz.foodmenu.R
import com.mdelacruz.foodmenu.ui.foodmenu.activity.FoodMenuActivity
import com.mdelacruz.foodmenu.util.start
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_welcome)

    showMenuButton.setOnClickListener(showMenuClickListener)
  }

  private val showMenuClickListener = View.OnClickListener {
    start(FoodMenuActivity::class.java)
  }

}