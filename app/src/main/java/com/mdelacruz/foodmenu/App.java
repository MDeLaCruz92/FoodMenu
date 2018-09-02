package com.mdelacruz.foodmenu;

import android.app.Application;

import com.mdelacruz.foodmenu.injection.component.AppComponent;
import com.mdelacruz.foodmenu.injection.component.DaggerAppComponent;
import com.mdelacruz.foodmenu.injection.module.AppModule;

public class App extends Application {
  private static App instance;

  public static App getInstance() {
    return instance;
  }

  protected AppComponent appComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    instance = this;
    appComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .build();
    appComponent.inject(this);
  }

  public AppComponent getAppComponent() {
    return appComponent;
  }
}
