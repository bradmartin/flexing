package net.bradmartin.flexing

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.hardware.camera2.CameraMetadata
import android.support.media.ExifInterface
import android.util.Log
import android.widget.ImageView
import java.io.ByteArrayInputStream
import java.io.IOException

const val TAG = "Flexing.Images"

fun getBitmapFromImageView(value: ImageView): Bitmap {
    // if not instance of ImageView throw an exception
    if (value !is ImageView) throw IllegalArgumentException("Value passed to getBitmapFromImageView is not of type ImageView")

    val drawable = value.drawable as BitmapDrawable
    return drawable.bitmap
}

/**
 * Returns the exif data from the camera byte array
 */
fun getOrientationFromBytes(data: ByteArray, cameraId: Int): Int {
    val inputStream = ByteArrayInputStream(data)
    val exif = android.support.media.ExifInterface(inputStream)
    var orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED)

    try {
        inputStream.close()
    } catch (error: IOException) {
        Log.e(TAG, "Error closing ByteArrayInputStream $error")
    }

    if (cameraId == CameraMetadata.LENS_FACING_BACK) {
        when (orientation) {
            ExifInterface.ORIENTATION_NORMAL -> orientation = ExifInterface.ORIENTATION_FLIP_HORIZONTAL
            ExifInterface.ORIENTATION_ROTATE_180 -> orientation = ExifInterface.ORIENTATION_FLIP_VERTICAL
            ExifInterface.ORIENTATION_ROTATE_90 -> orientation = ExifInterface.ORIENTATION_TRANSVERSE
        }
    }

    Log.i(TAG, "Orientation $orientation")
    return orientation
}