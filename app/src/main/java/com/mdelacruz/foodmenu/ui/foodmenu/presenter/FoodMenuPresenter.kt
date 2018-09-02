package com.mdelacruz.foodmenu.ui.foodmenu.presenter

import com.mdelacruz.foodmenu.base.BasePresenter
import com.mdelacruz.foodmenu.data.api.FoodApi
import com.mdelacruz.foodmenu.manager.FoodManager
import com.mdelacruz.foodmenu.ui.foodmenu.view.FoodMenuView
import com.mdelacruz.foodmenu.util.*
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class FoodMenuPresenter @Inject constructor(private var api: FoodApi,
                                            disposable: CompositeDisposable,
                                            scheduler: SchedulerProvider)
  : BasePresenter<FoodMenuView>(disposable, scheduler) {

  fun onInternetReconnected() {
    fetchFoodMenu()
  }

  fun fetchFoodMenu() {
    view?.showLoadingIndicator()
    disposable.add(api.fetchFoodMenu()
        .subscribeOn(scheduler.io())
        .observeOn(scheduler.ui())
        .subscribe(
            {
              FoodManager.savedFoodList.addAll(it)
              view?.hideLoadingIndicator()
              view?.showFoodMenu(FoodManager.savedFoodList)
              FoodManager.savedFoodList = mutableListOf()
            },
            {
              view?.hideLoadingIndicator()
              view?.showFetchMenuError()
            }
        ))
  }

}