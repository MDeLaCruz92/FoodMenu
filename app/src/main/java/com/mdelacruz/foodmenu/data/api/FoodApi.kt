package com.mdelacruz.foodmenu.data.api

import com.mdelacruz.foodmenu.data.model.Food
import com.mdelacruz.foodmenu.util.FOOD_COLLECTION
import io.reactivex.Observable
import retrofit2.http.GET

interface FoodApi {

  @GET(FOOD_COLLECTION)
  fun fetchFoodMenu(): Observable<List<Food>>
}