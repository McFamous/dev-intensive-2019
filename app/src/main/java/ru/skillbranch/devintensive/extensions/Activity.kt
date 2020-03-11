package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout


fun Activity.hideKeyboard(){
    val imm : InputMethodManager = applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}
fun Activity.isKeyboardOpen(rootView : View) : Boolean{

    /* 128dp = 32dp * 4, minimum button height 32dp and generic 4 rows soft keyboard */
    val SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD = 128
    val r = Rect()
    rootView.getWindowVisibleDisplayFrame(r)
    val dm: DisplayMetrics = rootView.resources.displayMetrics
    /* heightDiff = rootView height - status bar height (r.top) - visible frame height (r.bottom - r.top) */
    /* heightDiff = rootView height - status bar height (r.top) - visible frame height (r.bottom - r.top) */
    val heightDiff: Int = rootView.bottom - r.bottom
    /* Threshold size: dp to pixels, multiply with display density */
    /* Threshold size: dp to pixels, multiply with display density */
    val isKeyboardShown: Boolean = heightDiff > SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD * dm.density

    if(isKeyboardShown)
        Log.d("M_Activity","Keyboard open")
    else
        Log.d("M_Activity","Keyboard close")

    return isKeyboardShown
}
fun Activity.isKeyboardClosed(rootView : View) : Boolean{
    /* 128dp = 32dp * 4, minimum button height 32dp and generic 4 rows soft keyboard */
    val SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD = 128;

    val r = Rect()
    rootView.getWindowVisibleDisplayFrame(r)
    val dm: DisplayMetrics = rootView.resources.displayMetrics
    /* heightDiff = rootView height - status bar height (r.top) - visible frame height (r.bottom - r.top) */
    /* heightDiff = rootView height - status bar height (r.top) - visible frame height (r.bottom - r.top) */
    val heightDiff: Int = rootView.bottom - r.bottom
    /* Threshold size: dp to pixels, multiply with display density */
    /* Threshold size: dp to pixels, multiply with display density */
    val isKeyboardShown: Boolean = heightDiff > SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD * dm.density

    Log.d("M_Activity","Keyboard open")

    return isKeyboardShown
}