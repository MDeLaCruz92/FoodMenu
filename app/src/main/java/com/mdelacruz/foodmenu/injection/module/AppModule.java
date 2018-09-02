package com.mdelacruz.foodmenu.injection.module;

import android.app.Application;
import android.content.Context;

import com.mdelacruz.foodmenu.data.api.FoodApi;
import com.mdelacruz.foodmenu.injection.ForApplication;
import com.mdelacruz.foodmenu.ui.foodmenu.presenter.FoodMenuPresenter;
import com.mdelacruz.foodmenu.util.AppSchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class AppModule {
  private Application application;

  public AppModule(Application application) {
    this.application = application;
  }

  @Provides
  @Singleton
  @ForApplication
  Context provideContext() {
    return application;
  }

  @Provides
  @Singleton
  FoodMenuPresenter providesFoodMenuPresenter(FoodApi api,
                                              CompositeDisposable disposable,
                                              AppSchedulerProvider scheduler) {

    return new FoodMenuPresenter(api, disposable, scheduler);
  }

  @Provides
  @Singleton
  CompositeDisposable provideCompositeDisposable() {
    return new CompositeDisposable();
  }

  @Provides
  @Singleton
  AppSchedulerProvider provideSchedulerProvider() {
    return new AppSchedulerProvider();
  }
}
