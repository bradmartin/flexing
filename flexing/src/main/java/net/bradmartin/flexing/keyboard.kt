package net.bradmartin.flexing

import android.app.Activity
import android.view.inputmethod.InputMethodManager

fun hideSoftKeyboard(activity: Activity) {
    if (activity.currentFocus != null) {
        val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus.windowToken, 0)
    }
}