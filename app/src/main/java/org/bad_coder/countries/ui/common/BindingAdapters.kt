package org.bad_coder.countries.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import org.bad_coder.countries.R

class BindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("loadCircularImage")
        fun loadCircularImage(thumbs: ImageView, url: String) {
            Glide.with(thumbs)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.ic_flag_circle)
                .error(R.drawable.ic_flag_circle)
                .fallback(R.drawable.ic_flag_circle)
                .into(thumbs)
        }

        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(thumbs: ImageView, url: String) {
            Glide.with(thumbs)
                .load(url)
                .placeholder(R.drawable.ic_flag_circle)
                .error(R.drawable.ic_flag_circle)
                .fallback(R.drawable.ic_flag_circle)
                .into(thumbs)
        }
    }
}