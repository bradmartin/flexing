package net.bradmartin.flexing

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView

fun getBitmapFromImageView(value: ImageView): Bitmap {
    // if not instance of ImageView throw an exception
    if (value !is ImageView) {
        throw IllegalArgumentException("Value passed to getBitmapFromImageView is not of type ImageView")
    }

    val drawable = value.drawable as BitmapDrawable
    return drawable.bitmap
}
