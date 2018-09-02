package com.mdelacruz.foodmenu.ui.foodmenu.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.mdelacruz.foodmenu.R
import com.mdelacruz.foodmenu.data.model.Food
import com.mdelacruz.foodmenu.util.BASE_URL
import com.mdelacruz.foodmenu.util.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_menu.view.*

class FoodMenuViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
  fun bind(food: Food, clickListener: (Food) -> Unit) {
    view.foodTitleTextView?.text = food.title

    val avatarImageView = view.foodImageView
    Picasso.get()
        .load(BASE_URL + food.image)
        .placeholder(R.drawable.ic_restaurant_menu)
        .error(R.drawable.ic_restaurant_menu)
        .transform(CircleTransform)
        .into(avatarImageView)

    view.setOnClickListener { clickListener(food) }

    avatarImageView.setOnClickListener {
      clickListener(food)
    }
  }
}