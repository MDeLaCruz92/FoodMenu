package com.mdelacruz.foodmenu.injection.component

import com.mdelacruz.foodmenu.App
import com.mdelacruz.foodmenu.injection.module.AppModule
import com.mdelacruz.foodmenu.injection.module.NetworkModule
import com.mdelacruz.foodmenu.ui.foodmenu.activity.FoodMenuActivity
import com.mdelacruz.foodmenu.ui.foodmenu.activity.FoodSelectedActivity
import com.mdelacruz.foodmenu.ui.foodmenu.presenter.FoodMenuPresenter
import com.mdelacruz.foodmenu.util.AppSchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
  fun inject(app: App)

  fun inject(foodMenuActivity: FoodMenuActivity)

  fun inject(foodMenuPresenter: FoodMenuPresenter)

  fun inject(compositeDisposable: CompositeDisposable)

  fun inject(appSchedulerProvider: AppSchedulerProvider)

  fun inject(foodSelectedActivity: FoodSelectedActivity)
}