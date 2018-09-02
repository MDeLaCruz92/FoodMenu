package com.mdelacruz.foodmenu.ui.foodmenu.activity

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.mdelacruz.foodmenu.R
import com.mdelacruz.foodmenu.base.BaseActivity
import com.mdelacruz.foodmenu.data.model.Food
import com.mdelacruz.foodmenu.ui.foodmenu.adapter.FoodMenuAdapter
import com.mdelacruz.foodmenu.ui.foodmenu.presenter.FoodMenuPresenter
import com.mdelacruz.foodmenu.ui.foodmenu.view.FoodMenuView
import com.mdelacruz.foodmenu.util.*
import com.mdelacruz.foodmenu.util.InternetConnectivityReceiver.InternetConnectivityReceiverListener
import kotlinx.android.synthetic.main.activity_food_menu.*
import javax.inject.Inject

class FoodMenuActivity : BaseActivity(), FoodMenuView, InternetConnectivityReceiverListener {

  companion object {
    const val TAG_FOOD = "TAG_FOOD"
  }

  //==============================================================================================
  // Class Properties
  //==============================================================================================

  @Inject
  lateinit var foodMenuPresenter: FoodMenuPresenter

  private var foodMenuAdapter = FoodMenuAdapter(this) { foodItem : Food -> foodItemClicked(foodItem) }

  private var internetConnectivityReceiver: InternetConnectivityReceiver? = null

  private lateinit var linearLayoutManager: LinearLayoutManager

  //==============================================================================================
  // Lifecycle Methods
  //==============================================================================================

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_food_menu)

    // TODO: Put this in the base activity
    val actionBar = supportActionBar
    actionBar?.setDisplayHomeAsUpEnabled(true)
    actionBar?.setDisplayShowHomeEnabled(true)

    recyclerView.adapter = foodMenuAdapter
    linearLayoutManager = LinearLayoutManager(this)
    recyclerView.layoutManager = linearLayoutManager
    recyclerView.setHasFixedSize(true)

    internetConnectivityReceiver = InternetConnectivityReceiver(applicationContext, this, IntentFilter())
  }

  override fun onResume() {
    super.onResume()
    internetConnectivityReceiver?.register()
  }

  override fun onPause() {
    super.onPause()
    internetConnectivityReceiver?.unregister()
  }

  override fun onDestroy() {
    internetConnectivityReceiver?.destroy()
    internetConnectivityReceiver = null
    super.onDestroy()
  }

  override fun onOptionsItemSelected(menuItem: MenuItem?): Boolean {
    if (menuItem?.itemId == android.R.id.home) {
      onBackPressed()
      return true
    }
    return super.onOptionsItemSelected(menuItem)
  }

  override fun onActivityInject() {
    getAppComponent().inject(this)
    foodMenuPresenter.attachView(this)
  }

  private fun foodItemClicked(foodItem: Food) {
    val foodIntent = Intent(this, FoodSelectedActivity::class.java)
    foodIntent.putExtra(TAG_FOOD, foodItem)
    startActivity(foodIntent)
  }

  //==============================================================================================
  // InternetConnectivityReceiver Implementation
  //==============================================================================================

  override fun onInternetConnectivityChanged(isConnected: Boolean) {
    if (isConnected) {
      foodMenuPresenter.onInternetReconnected()
    }
  }

  //==============================================================================================
  // FoodMenuView Implementation
  //==============================================================================================

  override fun showLoadingIndicator() {
    progressBar.visible()
  }

  override fun hideLoadingIndicator() {
    progressBar.gone()
  }

  override fun showFoodMenu(foodMenu: List<Food>) {
    foodMenuAdapter.updateFoodMenu(foodMenu)
  }

  override fun showFetchMenuError() {
    LogUtils.e(TAG_ERROR, "There was an error fetching the food menu")
    toast(R.string.menu_fetch_error)
  }

}
