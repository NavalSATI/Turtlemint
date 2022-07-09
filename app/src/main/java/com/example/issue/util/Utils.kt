package com.example.issue.util

import android.content.Context
import com.example.issue.R
import android.view.WindowManager
import android.view.Gravity
import android.view.LayoutInflater
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.Glide

object Utils {
    fun dpToPx(context: Context, dp: Int): Int {
        val density = context.resources
            .displayMetrics.density
        return Math.round(dp.toFloat() * density)
    }
}