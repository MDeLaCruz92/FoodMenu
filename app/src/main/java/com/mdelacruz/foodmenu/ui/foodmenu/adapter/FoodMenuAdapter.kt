package com.mdelacruz.foodmenu.ui.foodmenu.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mdelacruz.foodmenu.R
import com.mdelacruz.foodmenu.data.model.Food
import com.mdelacruz.foodmenu.ui.foodmenu.viewholder.FoodMenuViewHolder

class FoodMenuAdapter(private val context: Context, private val clickListener: (Food) -> Unit) :
    RecyclerView.Adapter<FoodMenuViewHolder>() {

  private var foodMenu: List<Food> = listOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodMenuViewHolder {
    val layoutInflater = LayoutInflater.from(context)
    val itemView = layoutInflater.inflate(R.layout.list_item_menu, parent, false)
    return FoodMenuViewHolder(itemView)
  }

  override fun onBindViewHolder(holder: FoodMenuViewHolder, position: Int) {
    holder.bind(foodMenu[position], clickListener)
  }

  override fun getItemCount(): Int {
    return foodMenu.size
  }

  fun updateFoodMenu(foodMenu: List<Food>) {
    this.foodMenu = foodMenu
    notifyDataSetChanged()
  }

}