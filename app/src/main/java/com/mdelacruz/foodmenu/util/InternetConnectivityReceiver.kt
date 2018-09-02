package com.mdelacruz.foodmenu.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager


/**
 * A BroadcastReceiver that can be registered to listen for internet connectivity events, such
 * as when internet disconnects and reconnects. Can also determine if we are currently connected to
 * the Internet or not.
 *
 * Created on 6/18/18.
 *
 * @author Michael De La Cruz
 */
class InternetConnectivityReceiver(context: Context,
                                   private var internetConnectivityReceiverListener: InternetConnectivityReceiverListener?,
                                   private var intentFilter: IntentFilter) : BroadcastReceiver() {
  //==============================================================================================
  // Class Properties
  //==============================================================================================

  private var context: Context? = null
  private var isRegistered: Boolean = false

  private val isConnected: Boolean
    get() {
      val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      val activeNetworkInfo = connectivityManager.activeNetworkInfo

      return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

  init {
    this.context = context.applicationContext

    this.intentFilter = IntentFilter()
    this.intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
  }

  //==============================================================================================
  // Class Instance Methods
  //==============================================================================================

  fun register() {
    if (!isRegistered) {
      context?.registerReceiver(this, intentFilter)
      isRegistered = true
    }
  }

  fun unregister() {
    if (isRegistered) {
      context?.unregisterReceiver(this)
      isRegistered = false
    }
  }

  fun destroy() {
    context = null
    internetConnectivityReceiverListener = null
  }

  //==============================================================================================
  // Broadcast Receiver Methods
  //==============================================================================================

  override fun onReceive(context: Context, intent: Intent) {
    if (intent.action == ConnectivityManager.CONNECTIVITY_ACTION) {
      internetConnectivityReceiverListener?.onInternetConnectivityChanged(isConnected)
    }
  }

  //==============================================================================================
  // InternetConnectivityReceiverListener Interface
  //==============================================================================================

  interface InternetConnectivityReceiverListener {
    fun onInternetConnectivityChanged(isConnected: Boolean)
  }
}