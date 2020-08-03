package com.shakib.assesment.helper

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object UIHelper {

    @JvmStatic
    @BindingAdapter("loadImageFromUrl")
    fun loadImage(view: ImageView, imageUrl: String?) {
        val options = RequestOptions()

        Glide.with(view.context).load(imageUrl).apply(options).into(view)
    }

    fun getFullName(firstName: String, lastName: String): String = "$firstName $lastName"
}