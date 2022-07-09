package com.example.issue.util

import android.app.Dialog
import android.content.Context
import com.example.issue.R
import android.view.WindowManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.Glide

class TransparentProgressDialog : Dialog {
    private var iv: ImageView

    constructor(context: Context) : super(context, R.style.TransparentProgressDialog1) {
        val wlmp = window!!.attributes
        wlmp.gravity = Gravity.CENTER_HORIZONTAL
        window!!.attributes = wlmp
        setTitle(null)
        setCancelable(false)
        setOnCancelListener(null)
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.loader_layout, null)
        iv = view.findViewById<View>(R.id.imageView) as ImageView
        iv.layoutParams.height = Utils.dpToPx(context, 65)
        iv.layoutParams.width = Utils.dpToPx(context, 65)
        val drawableImageViewTarget = DrawableImageViewTarget(iv)
        Glide.with(context).load(R.raw.progress_bar_new).into(drawableImageViewTarget)
        setContentView(view)
    }

    constructor(context: Context, isShowCoustomizingGif: Boolean) : super(
        context,
        R.style.TransparentProgressDialog1
    ) {
        val wlmp = window!!.attributes
        wlmp.gravity = Gravity.CENTER_HORIZONTAL
        window!!.attributes = wlmp
        setTitle(null)
        setCancelable(false)
        setOnCancelListener(null)
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.loader_layout, null)
        iv = view.findViewById<View>(R.id.imageView) as ImageView
        val drawableImageViewTarget = DrawableImageViewTarget(iv)
        if (isShowCoustomizingGif) {
            iv.layoutParams.height = Utils.dpToPx(context, 300)
            iv.layoutParams.width = Utils.dpToPx(context, 300)
            iv.scaleType = ImageView.ScaleType.FIT_XY
            Glide.with(context).load(R.raw.customizing_content).into(drawableImageViewTarget)
        } else {
            iv.layoutParams.height = Utils.dpToPx(context, 100)
            Glide.with(context).load(R.raw.progress_bar_new).into(drawableImageViewTarget)
        }
        setContentView(view)
    }

    fun setMessage(s: String?) {}
    override fun show() {
        if (this != null) {
            super.show()
        }
    }
}