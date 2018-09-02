package com.mdelacruz.foodmenu.base

interface Presenter<V : BaseView> {
  fun attachView(view: V)
  fun detachView()
}