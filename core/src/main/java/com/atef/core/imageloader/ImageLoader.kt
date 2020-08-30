package com.atef.core.imageloader

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(view: ImageView, url: String)
}
