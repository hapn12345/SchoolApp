package com.example.datn_project.Utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.WindowManager

fun makeStatusBarTransparent(activity: Activity) {
    activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    activity.window.statusBarColor = Color.TRANSPARENT
    activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
}
