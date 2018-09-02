package com.mdelacruz.foodmenu.util

import android.util.Log
import com.mdelacruz.foodmenu.BuildConfig

/**
 * A logging utility class that should be used in place of the standard
 * {@link Log} class
 * <p/>
 * This class makes sure that only error and warning logs are printed out
 * for release builds.
 * <p/>
 * Created on 6/16/18.
 *
 * @author Michael De La Cruz
 */
@Suppress("PointlessBooleanExpression")
object LogUtils {

  /**
   * Send a {@link Log#DEBUG} log message.
   *
   * @param tag Used to identify the source of a log message. It usually identifies
   * the class or activity where the log call occurs.
   * @param msg The message you would like logged.
   */
  fun d(tag: String, msg: String): Int {
    if (!BuildConfig.DEBUG) {
      return 0
    }
    return Log.d(tag, msg)
  }

  /**
   * Send an {@link Log#INFO} log message.
   *
   * @param tag Used to identify the source of a log message. It usually identifies
   * the class or activity where the log call occurs.
   * @param msg The message you would like logged.
   */
  fun i(tag: String, msg: String): Int {
    if (!BuildConfig.DEBUG) {
      return 0
    }
    return Log.i(tag, msg)
  }

  /**
   * Send a {@link Log#INFO} log message and log the exception.
   *
   * @param tag Used to identify the source of a log message. It usually identifies
   * the class or activity where the log call occurs.
   * @param msg The message you would like logged.
   * @param tr An exception to log
   */
  fun i(tag: String, msg: String, tr: Throwable): Int {
    if (!BuildConfig.DEBUG) {
      return 0
    }
    return Log.i(tag, msg, tr)
  }

  /**
   * Send an {@link Log#ERROR} log message.
   *
   * @param tag Used to identify the source of a log message. It usually identifies
   * the class or activity where the log call occurs.
   * @param msg The message you would like logged.
   */
  fun e(tag: String, msg: String): Int {
    return Log.e(tag, msg)
  }

  /**
   * Send a {@link Log#ERROR} log message and log the exception.
   *
   * @param tag Used to identify the source of a log message. It usually identifies
   * the class or activity where the log call occurs.
   * @param msg The message you would like logged.
   * @param tr An exception to log
   */
  fun e(tag: String, msg: String, tr: Throwable): Int {
    return Log.e(tag, msg, tr)
  }
  // end region
}