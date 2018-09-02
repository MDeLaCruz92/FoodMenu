package com.mdelacruz.foodmenu.ui.foodmenu.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.mdelacruz.foodmenu.R
import com.mdelacruz.foodmenu.data.model.Food
import com.mdelacruz.foodmenu.util.BASE_URL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_food_selected.*

class FoodSelectedActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_food_selected)

    val actionBar = supportActionBar
    actionBar?.setDisplayHomeAsUpEnabled(true)
    actionBar?.setDisplayShowHomeEnabled(true)

    setupIntent()
  }

  override fun onOptionsItemSelected(menuItem: MenuItem?): Boolean {
    if (menuItem?.itemId == android.R.id.home) {
      onBackPressed()
      return true
    }
    return super.onOptionsItemSelected(menuItem)
  }
  
  private fun setupIntent() {
    intent?.let {
      val food = intent.getParcelableExtra<Food>(FoodMenuActivity.TAG_FOOD)
      food_selected_textview.text = food.title
      actionBar?.title = food.title

      Picasso.get()
          .load(BASE_URL + food.image)
          .into(image_selected_imageview)
    }
  }
}