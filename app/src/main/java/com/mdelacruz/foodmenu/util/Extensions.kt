package com.mdelacruz.foodmenu.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

//==================================================================================================
// Activity Related
//==================================================================================================

// used for simple start activity without Intent parameters
fun Activity.start(clazz: Class<out Activity>) {
  startActivity(Intent(this, clazz))
}

//==================================================================================================
// View Related
//==================================================================================================

fun View.visible() {
  visibility = View.VISIBLE
}

fun View.invisible() {
  visibility = View.INVISIBLE
}

fun View.gone() {
  visibility = View.GONE
}

//==================================================================================================
// String Related
//==================================================================================================

fun EditText.textString() = text.toString()


fun TextView.textString(): String {
  return text.toString()
}

//==================================================================================================
// Toast Related
//==================================================================================================

// used for show a toast message in the center of the screen
fun Context.toast(message: Int) {
  val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
  toast.setGravity(Gravity.CENTER, 0, 0)
  toast.show()
}