package com.mdelacruz.foodmenu.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mdelacruz.foodmenu.App
import com.mdelacruz.foodmenu.R
import com.mdelacruz.foodmenu.injection.component.AppComponent
import com.mdelacruz.foodmenu.util.toast

abstract class BaseActivity : AppCompatActivity(), BaseView {

  private var presenter: BasePresenter<*>? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    onActivityInject()
  }

  protected abstract fun onActivityInject()

  fun getAppComponent(): AppComponent = App.getInstance().appComponent

  override fun setPresenter(presenter: BasePresenter<*>) {
    this.presenter = presenter
  }

  override fun onError() {
    toast(R.string.unknown_error)
  }

  override fun onDestroy() {
    presenter?.detachView()
    presenter = null
    super.onDestroy()
  }
}