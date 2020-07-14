package com.em4n0101.mytvshows.utils

import android.os.Build
import android.text.Html
import android.view.View
import android.widget.ImageView
import androidx.core.text.HtmlCompat
import com.airbnb.lottie.LottieAnimationView
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.model.response.InnerImages
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

fun String.convertUrlStringFromHttpToHttps() = replace("http://", "https://")

fun String.removeHtmlTags() = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)

fun formatTimeToReadableText(date: String): String {
    val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    return formatter.format(parser.parse(date)!!)
}

fun setupImageForViewHolder(images: InnerImages?, intoImageView: ImageView, withLoaderView: LottieAnimationView) {
    if (images != null) {
        Picasso.get()
            .load(images.medium.convertUrlStringFromHttpToHttps())
            .error(R.drawable.no_image_available)
            .into(intoImageView)
    } else {
        withLoaderView.visibility = View.GONE
        intoImageView.setImageResource(R.drawable.no_image_available)
    }
}