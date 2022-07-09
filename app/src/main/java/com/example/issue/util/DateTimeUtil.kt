package com.example.issue.util

import com.example.issue.R
import android.view.WindowManager
import android.view.Gravity
import android.view.LayoutInflater
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.Glide
import java.text.ParseException
import java.text.SimpleDateFormat

object DateTimeUtil {
    fun convertDateToString(dateInput: String?): String? {
        try {
            val inputFormat = SimpleDateFormat("yyyy-mm-dd")
            val outputFormat = SimpleDateFormat("mm-dd-yyyy")
            val parsedDate = inputFormat.parse(dateInput)
            val formattedDate = outputFormat.format(parsedDate)
            println(formattedDate)
            return formattedDate
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return null
    }
}