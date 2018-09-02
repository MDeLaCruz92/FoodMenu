package com.mdelacruz.foodmenu.ui.foodmenu.view

import com.mdelacruz.foodmenu.base.BaseView
import com.mdelacruz.foodmenu.data.model.Food

interface FoodMenuView : BaseView {
  fun showLoadingIndicator()
  fun hideLoadingIndicator()
  fun showFoodMenu(foodMenu: List<Food>)
  fun showFetchMenuError()
}