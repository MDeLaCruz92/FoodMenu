package com.mdelacruz.foodmenu.util

import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider constructor(private val testSchedulerProvider: TestScheduler): SchedulerProvider {
  override fun ui(): Scheduler = testSchedulerProvider
  override fun computation(): Scheduler = testSchedulerProvider
  override fun io(): Scheduler = testSchedulerProvider
}