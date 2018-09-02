package com.mdelacruz.foodmenu.base

interface BaseView {
  fun onError()
  fun setPresenter(presenter: BasePresenter<*>)
}