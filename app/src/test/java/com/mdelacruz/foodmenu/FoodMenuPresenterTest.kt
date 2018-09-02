package com.mdelacruz.foodmenu

import com.mdelacruz.foodmenu.data.api.FoodApi
import com.mdelacruz.foodmenu.manager.FoodManager
import com.mdelacruz.foodmenu.ui.foodmenu.presenter.FoodMenuPresenter
import com.mdelacruz.foodmenu.ui.foodmenu.view.FoodMenuView
import com.mdelacruz.foodmenu.util.TestSchedulerProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class FoodMenuPresenterTest {

  private val view = Mockito.mock(FoodMenuView::class.java)
  private val api = Mockito.mock(FoodApi::class.java)
  private lateinit var presenter: FoodMenuPresenter
  private lateinit var testScheduler: TestScheduler

  @Before
  fun setup() {
    val compositeDisposable = CompositeDisposable()
    testScheduler = TestScheduler()
    val testSchedulerProvider = TestSchedulerProvider(testScheduler)
    presenter = FoodMenuPresenter(api, compositeDisposable, testSchedulerProvider)
    presenter.attachView(view)
  }

  @Test
  fun test_fetchFoodMenu_should_callSuccess() {
    val mockedResponse = Mockito.mock(FoodManager::class.java)

    Mockito.doReturn(Observable.just(mockedResponse.savedFoodList))
        .`when`(api)
        .fetchFoodMenu()

    presenter.fetchFoodMenu()

    testScheduler.triggerActions()

    Mockito.verify(view).showLoadingIndicator()
    Mockito.verify(view).showFoodMenu(FoodManager.savedFoodList)
    Mockito.verify(view).hideLoadingIndicator()
  }

  @Test
  fun test_fetchMenu_should_callError() {
    val mockedResponse = Mockito.mock(Throwable::class.java)

    Mockito.doReturn(Observable.just(mockedResponse))
        .`when`(api)
        .fetchFoodMenu()

    presenter.fetchFoodMenu()

    testScheduler.triggerActions()

    Mockito.verify(view).showLoadingIndicator()
    Mockito.verify(view).showFetchMenuError()
    Mockito.verify(view).hideLoadingIndicator()
  }

  @After
  fun tearDown() {
    presenter.detachView()
  }
}